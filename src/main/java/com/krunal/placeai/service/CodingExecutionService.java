package com.krunal.placeai.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krunal.placeai.dto.CodingSubmissionRequest;
import com.krunal.placeai.dto.CodingSubmissionResponse;
import com.krunal.placeai.entity.CodingProblem;

@Service
public class CodingExecutionService {

    @Autowired
    private CodingProblemService codingProblemService;


    public CodingSubmissionResponse submit(
            CodingSubmissionRequest request) {

        try {

            if (request == null) {

                return response(
                        false,
                        "Request is empty.",
                        "",
                        "",
                        ""
                );
            }


            if (request.getProblemId() == null) {

                return response(
                        false,
                        "Problem ID is required.",
                        "",
                        "",
                        ""
                );
            }


            if (request.getCode() == null ||
                    request.getCode().trim().isEmpty()) {

                return response(
                        false,
                        "Please write your code first.",
                        "",
                        "",
                        ""
                );
            }


            CodingProblem problem =
                    codingProblemService.getProblem(
                            request.getProblemId()
                    );


            String language =
                    request.getLanguage();

            if (language == null ||
                    language.isBlank()) {

                language =
                        problem.getLanguage();
            }


            String input =
                    problem.getSampleInput();

            if (input == null) {
                input = "";
            }


            String expected =
                    problem.getExpectedOutput();

            if (expected == null) {
                expected = "";
            }


            Path directory =
                    Files.createTempDirectory(
                            "placeai-" +
                            UUID.randomUUID()
                    );


            try {

                ExecutionResult result =
                        execute(
                                language,
                                request.getCode(),
                                input,
                                directory
                        );


                String actual =
                        normalize(
                                result.output()
                        );

                String expectedNormalized =
                        normalize(
                                expected
                        );


                boolean passed =
                        result.success()
                        &&
                        actual.equals(
                                expectedNormalized
                        );


                if (!result.success()) {

                    return response(
                            false,
                            "❌ Code execution failed.",
                            actual,
                            expected,
                            result.error()
                    );
                }


                if (passed) {

                    return response(
                            true,
                            "✅ Sample test case passed.",
                            actual,
                            expected,
                            ""
                    );
                }


                return response(
                        false,
                        "❌ Sample test case failed.",
                        actual,
                        expected,
                        ""
                );

            } finally {

                deleteDirectory(
                        directory
                );
            }


        } catch (Exception e) {

            e.printStackTrace();

            return response(
                    false,
                    "❌ Backend execution error.",
                    "",
                    "",
                    e.getMessage()
            );
        }
    }


    private ExecutionResult execute(
            String language,
            String sourceCode,
            String input,
            Path directory)
            throws Exception {


        if (language == null) {

            return new ExecutionResult(
                    false,
                    "",
                    "Language is missing."
            );
        }


        String lang =
                language.trim()
                        .toLowerCase();


        // ================= JAVA =================

        if (lang.equals("java")) {

            Path source =
                    directory.resolve(
                            "Main.java"
                    );


            Files.writeString(
                    source,
                    sourceCode,
                    StandardCharsets.UTF_8
            );


            ExecutionResult compile =
                    run(
                            directory,
                            10,
                            "",
                            "javac",
                            "Main.java"
                    );


            if (!compile.success()) {

                return compile;
            }


            return run(
                    directory,
                    5,
                    input,
                    "java",
                    "-cp",
                    directory.toString(),
                    "Main"
            );
        }


        // ================= PYTHON =================

        if (lang.equals("python")) {

            Path source =
                    directory.resolve(
                            "solution.py"
                    );


            Files.writeString(
                    source,
                    sourceCode,
                    StandardCharsets.UTF_8
            );


            return run(
                    directory,
                    5,
                    input,
                    "python",
                    source.toString()
            );
        }


        // ================= C++ =================

        if (lang.equals("c++")) {

            Path source =
                    directory.resolve(
                            "main.cpp"
                    );

            Path executable =
                    directory.resolve(
                            "main.exe"
                    );


            Files.writeString(
                    source,
                    sourceCode,
                    StandardCharsets.UTF_8
            );


            ExecutionResult compile =
                    run(
                            directory,
                            10,
                            "",
                            "g++",
                            source.toString(),
                            "-std=c++17",
                            "-O2",
                            "-o",
                            executable.toString()
                    );


            if (!compile.success()) {

                return compile;
            }


            return run(
                    directory,
                    5,
                    input,
                    executable.toString()
            );
        }


        return new ExecutionResult(
                false,
                "",
                "Unsupported language: " +
                        language
        );
    }


    private ExecutionResult run(
            Path directory,
            int timeoutSeconds,
            String input,
            String... command)
            throws Exception {


        ProcessBuilder builder =
                new ProcessBuilder(
                        command
                );


        builder.directory(
                directory.toFile()
        );


        builder.redirectErrorStream(
                true
        );


        Process process =
                builder.start();


        if (input != null &&
                !input.isEmpty()) {

            process.getOutputStream()
                    .write(
                            input.getBytes(
                                    StandardCharsets.UTF_8
                            )
                    );
        }


        process.getOutputStream()
                .close();


        boolean completed =
                process.waitFor(
                        timeoutSeconds,
                        TimeUnit.SECONDS
                );


        if (!completed) {

            process.destroyForcibly();

            return new ExecutionResult(
                    false,
                    "",
                    "Execution timed out."
            );
        }


        String output =
                new String(
                        process.getInputStream()
                                .readAllBytes(),
                        StandardCharsets.UTF_8
                );


        int exitCode =
                process.exitValue();


        if (exitCode != 0) {

            return new ExecutionResult(
                    false,
                    output,
                    "Process exited with code " +
                    exitCode
            );
        }


        return new ExecutionResult(
                true,
                output,
                ""
        );
    }


    private String normalize(
            String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .trim()
                .replaceAll(
                        "\\s+",
                        " "
                );
    }


    private CodingSubmissionResponse response(
            boolean passed,
            String message,
            String actual,
            String expected,
            String error) {

        return new CodingSubmissionResponse(
                passed,
                message,
                actual,
                expected,
                error
        );
    }


    private void deleteDirectory(
            Path directory) {

        if (directory == null) {
            return;
        }


        try {

            Files.walk(directory)
                    .sorted(
                            (a, b) ->
                                    b.compareTo(a)
                    )
                    .forEach(
                            path -> {
                                try {
                                    Files.deleteIfExists(
                                            path
                                    );
                                } catch (IOException ignored) {
                                }
                            }
                    );

        } catch (IOException ignored) {
        }
    }


    private record ExecutionResult(
            boolean success,
            String output,
            String error) {
    }
}