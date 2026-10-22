package com.krunal.placeai.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.krunal.placeai.entity.AwtQuestion;
import com.krunal.placeai.entity.CodingProblem;
import com.krunal.placeai.repository.AwtQuestionRepository;
import com.krunal.placeai.repository.CodingProblemRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedData(
            AwtQuestionRepository awtRepository,
            CodingProblemRepository codingRepository) {

        return args -> {

            // =====================================================
            // APTITUDE QUESTIONS
            // =====================================================

            if (awtRepository.count() == 0) {

                awtRepository.saveAll(List.of(

                        q(
                                "What is 20% of 250?",
                                "40",
                                "50",
                                "60",
                                "70",
                                "B",
                                "quantitative",
                                "easy",
                                "20/100 × 250 = 50."
                        ),

                        q(
                                "A train travels 120 km in 2 hours. What is its average speed?",
                                "40 km/h",
                                "50 km/h",
                                "60 km/h",
                                "80 km/h",
                                "C",
                                "quantitative",
                                "easy",
                                "Speed = Distance / Time = 120 / 2 = 60 km/h."
                        ),

                        q(
                                "If the ratio of boys to girls is 3:2 and there are 30 boys, how many girls are there?",
                                "15",
                                "20",
                                "25",
                                "30",
                                "B",
                                "quantitative",
                                "easy",
                                "3 parts = 30, so 1 part = 10. Girls = 2 × 10 = 20."
                        ),

                        q(
                                "The average of 10, 20 and 30 is:",
                                "15",
                                "20",
                                "25",
                                "30",
                                "B",
                                "quantitative",
                                "easy",
                                "(10 + 20 + 30) / 3 = 20."
                        ),

                        q(
                                "If x + 7 = 19, what is x?",
                                "10",
                                "11",
                                "12",
                                "13",
                                "C",
                                "quantitative",
                                "easy",
                                "x = 19 - 7 = 12."
                        ),

                        q(
                                "A product costs ₹500 and is sold at a 10% discount. What is the selling price?",
                                "₹440",
                                "₹450",
                                "₹460",
                                "₹470",
                                "B",
                                "quantitative",
                                "easy",
                                "10% of ₹500 = ₹50. Selling price = ₹450."
                        ),

                        q(
                                "A can complete a job in 12 days and B in 18 days. Working together, they finish it in:",
                                "6 days",
                                "7.2 days",
                                "8 days",
                                "9 days",
                                "B",
                                "quantitative",
                                "medium",
                                "Combined rate = 1/12 + 1/18 = 5/36. Time = 36/5 = 7.2 days."
                        ),

                        q(
                                "A number is increased by 25% and becomes 500. What was the original number?",
                                "375",
                                "400",
                                "425",
                                "450",
                                "B",
                                "quantitative",
                                "medium",
                                "Original × 1.25 = 500, so original = 400."
                        ),

                        q(
                                "A sum becomes ₹1200 at 10% simple interest in 2 years. What is the principal?",
                                "₹900",
                                "₹950",
                                "₹1000",
                                "₹1100",
                                "C",
                                "quantitative",
                                "medium",
                                "1200 = P(1 + 10×2/100), so P = ₹1000."
                        ),

                        q(
                                "The probability of getting an even number on a fair six-sided die is:",
                                "1/6",
                                "1/3",
                                "1/2",
                                "2/3",
                                "C",
                                "quantitative",
                                "medium",
                                "Even outcomes are 2, 4 and 6. Probability = 3/6 = 1/2."
                        ),

                        q(
                                "If 5 workers make 100 units in 4 days, how many units will 10 workers make in 4 days?",
                                "150",
                                "200",
                                "250",
                                "300",
                                "B",
                                "quantitative",
                                "medium",
                                "Doubling workers doubles output. Answer = 200."
                        ),

                        q(
                                "A shopkeeper gains 20% by selling an item for ₹600. What is the cost price?",
                                "₹480",
                                "₹500",
                                "₹520",
                                "₹550",
                                "B",
                                "quantitative",
                                "medium",
                                "600 = 120% of CP. CP = 600/1.2 = ₹500."
                        ),

                        q(
                                "A train 150 m long crosses a pole in 10 seconds. Its speed is:",
                                "15 m/s",
                                "20 m/s",
                                "25 m/s",
                                "30 m/s",
                                "A",
                                "quantitative",
                                "hard",
                                "Speed = 150/10 = 15 m/s."
                        ),

                        q(
                                "The compound interest on ₹10,000 for 2 years at 10% p.a. is:",
                                "₹2000",
                                "₹2100",
                                "₹2200",
                                "₹2400",
                                "B",
                                "quantitative",
                                "hard",
                                "Amount = 10000 × 1.1² = 12100. CI = ₹2100."
                        ),

                        q(
                                "A mixture contains milk and water in ratio 5:1. If 12 litres water are added, ratio becomes 5:2. Initial mixture is:",
                                "60 L",
                                "72 L",
                                "84 L",
                                "90 L",
                                "B",
                                "quantitative",
                                "hard",
                                "Let milk = 5x and water = x. 5x/(x+12)=5/2, so x=12. Initial mixture = 72 L."
                        ),

                        q(
                                "Two numbers are in ratio 4:7 and their sum is 99. The smaller number is:",
                                "32",
                                "36",
                                "40",
                                "44",
                                "B",
                                "quantitative",
                                "hard",
                                "11 parts = 99. One part = 9. Smaller = 4×9 = 36."
                        ),

                        q(
                                "Find the next number: 2, 6, 12, 20, 30, ?",
                                "40",
                                "42",
                                "44",
                                "46",
                                "B",
                                "logical",
                                "easy",
                                "Differences are 4, 6, 8, 10. Next difference is 12. Answer = 42."
                        ),

                        q(
                                "If CAT is coded as DBU, how is DOG coded?",
                                "EPH",
                                "EOH",
                                "FPH",
                                "DPG",
                                "A",
                                "logical",
                                "easy",
                                "Every letter is shifted forward by one."
                        ),

                        q(
                                "Which one is different from the others?",
                                "Apple",
                                "Mango",
                                "Carrot",
                                "Banana",
                                "C",
                                "logical",
                                "easy",
                                "Carrot is a vegetable. The others are fruits."
                        ),

                        q(
                                "A is taller than B. B is taller than C. Who is shortest?",
                                "A",
                                "B",
                                "C",
                                "Cannot say",
                                "C",
                                "logical",
                                "easy",
                                "A > B > C, therefore C is shortest."
                        ),

                        q(
                                "Find the missing number: 3, 9, 27, 81, ?",
                                "162",
                                "243",
                                "324",
                                "729",
                                "B",
                                "logical",
                                "medium",
                                "Each number is multiplied by 3. 81 × 3 = 243."
                        ),

                        q(
                                "If all roses are flowers and some flowers are red, which is definitely true?",
                                "All roses are red",
                                "Some roses are red",
                                "All roses are flowers",
                                "No roses are red",
                                "C",
                                "logical",
                                "medium",
                                "The first statement directly says all roses are flowers."
                        ),

                        q(
                                "A person walks 5 km north and then 3 km east. Direction from starting point?",
                                "North-west",
                                "North-east",
                                "South-east",
                                "South-west",
                                "B",
                                "logical",
                                "medium",
                                "North + east gives north-east."
                        ),

                        q(
                                "In a queue, Rahul is 8th from front and 12th from back. Total people?",
                                "19",
                                "20",
                                "21",
                                "22",
                                "A",
                                "logical",
                                "medium",
                                "Total = 8 + 12 - 1 = 19."
                        ),

                        q(
                                "Choose the word closest in meaning to 'rapid'.",
                                "Slow",
                                "Fast",
                                "Weak",
                                "Late",
                                "B",
                                "verbal",
                                "easy",
                                "Rapid means fast or quick."
                        ),

                        q(
                                "Choose the opposite of 'ancient'.",
                                "Old",
                                "Historic",
                                "Modern",
                                "Traditional",
                                "C",
                                "verbal",
                                "easy",
                                "Modern is the opposite of ancient."
                        ),

                        q(
                                "Choose the correctly spelled word.",
                                "Accomodation",
                                "Accommodation",
                                "Acommodation",
                                "Accommadation",
                                "B",
                                "grammar",
                                "easy",
                                "Accommodation is the correct spelling."
                        ),

                        q(
                                "Fill in the blank: She ___ to college every day.",
                                "go",
                                "going",
                                "goes",
                                "gone",
                                "C",
                                "grammar",
                                "easy",
                                "With 'She', the correct verb is 'goes'."
                        ),

                        q(
                                "Choose the correct sentence.",
                                "He don't like tea.",
                                "He doesn't likes tea.",
                                "He doesn't like tea.",
                                "He not like tea.",
                                "C",
                                "grammar",
                                "medium",
                                "After doesn't, use the base verb 'like'."
                        ),

                        q(
                                "Fill in the blank: If I ___ enough money, I would buy a laptop.",
                                "have",
                                "had",
                                "will have",
                                "am having",
                                "B",
                                "grammar",
                                "medium",
                                "Second conditional uses 'if + past simple'."
                        ),

                        q(
                                "Which data structure follows LIFO order?",
                                "Queue",
                                "Stack",
                                "Linked List",
                                "Heap",
                                "B",
                                "technical",
                                "easy",
                                "Stack follows Last In First Out."
                        ),

                        q(
                                "Which SQL command is used to retrieve data?",
                                "INSERT",
                                "UPDATE",
                                "SELECT",
                                "DELETE",
                                "C",
                                "technical",
                                "easy",
                                "SELECT retrieves data from database tables."
                        ),

                        q(
                                "Which Java keyword is used to inherit a class?",
                                "implements",
                                "extends",
                                "inherits",
                                "super",
                                "B",
                                "technical",
                                "medium",
                                "Java uses extends for class inheritance."
                        ),

                        q(
                                "What is the time complexity of binary search?",
                                "O(1)",
                                "O(log n)",
                                "O(n)",
                                "O(n log n)",
                                "B",
                                "technical",
                                "medium",
                                "Binary search halves the search space each time."
                        ),

                        q(
                                "Which protocol is commonly used for secure web communication?",
                                "HTTP",
                                "FTP",
                                "HTTPS",
                                "SMTP",
                                "C",
                                "technical",
                                "hard",
                                "HTTPS uses TLS to secure HTTP communication."
                        ),

                        q(
                                "Which Java collection does not allow duplicate elements?",
                                "List",
                                "Set",
                                "ArrayList",
                                "Vector",
                                "B",
                                "technical",
                                "hard",
                                "Set does not allow duplicate elements."
                        ),

                        q(
                                "What does JVM stand for?",
                                "Java Variable Machine",
                                "Java Virtual Machine",
                                "Java Verified Machine",
                                "Java Visual Machine",
                                "B",
                                "technical",
                                "hard",
                                "JVM means Java Virtual Machine."
                        ),

                        q(
                                "Which normal form removes partial dependency?",
                                "1NF",
                                "2NF",
                                "3NF",
                                "BCNF",
                                "B",
                                "technical",
                                "hard",
                                "2NF removes partial dependency."
                        )
                ));

                System.out.println(
                        "PlaceAI: Aptitude questions seeded successfully."
                );
            }


            // =====================================================
            // CODING PROBLEMS
            // =====================================================

            if (codingRepository.count() == 0) {

                codingRepository.saveAll(List.of(

                        coding(
                                "Two Sum",
                                "Java",
                                "Easy",
                                "Amazon",
                                true,
                                "Given an array and a target, find two indices whose values add up to the target.",
                                "First line: n. Second line: n integers. Third line: target.",
                                "Print the two indices.",
                                "4\n2 7 11 15\n9",
                                "0 1",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        int n = sc.nextInt();\n        int[] a = new int[n];\n        for (int i = 0; i < n; i++) a[i] = sc.nextInt();\n        int target = sc.nextInt();\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int needed = target - a[i];

            if (map.containsKey(needed)) {
                System.out.println(map.get(needed) + " " + i);
                return;
            }

            map.put(a[i], i);
        }
    }
}
""",
                                "0 1"
                        ),

                        coding(
                                "Reverse a String",
                                "Java",
                                "Easy",
                                "TCS",
                                true,
                                "Reverse the given string.",
                                "Input contains one string.",
                                "Print the reversed string.",
                                "hello",
                                "olleh",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        String s = sc.nextLine();\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        String reversed =
                new StringBuilder(s).reverse().toString();

        System.out.println(reversed);
    }
}
""",
                                "olleh"
                        ),

                        coding(
                                "Maximum Element",
                                "Java",
                                "Easy",
                                "Wipro",
                                false,
                                "Find the maximum element in an integer array.",
                                "First line: n. Second line: n integers.",
                                "Print the maximum.",
                                "5\n3 9 2 7 4",
                                "9",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        int n = sc.nextInt();\n        int max = Integer.MIN_VALUE;\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, sc.nextInt());
        }

        System.out.println(max);
    }
}
""",
                                "9"
                        ),

                        coding(
                                "Palindrome Number",
                                "Java",
                                "Easy",
                                "Infosys",
                                false,
                                "Check whether an integer is a palindrome.",
                                "Input contains one integer.",
                                "Print true or false.",
                                "121",
                                "true",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        int n = sc.nextInt();\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int original = n;
        int reverse = 0;

        while (n != 0) {

            int digit = n % 10;
            reverse = reverse * 10 + digit;
            n /= 10;
        }

        System.out.println(original == reverse);
    }
}
""",
                                "true"
                        ),

                        coding(
                                "Count Vowels",
                                "Java",
                                "Easy",
                                "Accenture",
                                false,
                                "Count the number of vowels in a string.",
                                "Input contains one line.",
                                "Print the vowel count.",
                                "education",
                                "5",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        String s = sc.nextLine().toLowerCase();\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine().toLowerCase();

        int count = 0;

        for (char c : s.toCharArray()) {

            if (c == 'a' ||
                c == 'e' ||
                c == 'i' ||
                c == 'o' ||
                c == 'u') {

                count++;
            }
        }

        System.out.println(count);
    }
}
""",
                                "5"
                        ),

                        coding(
                                "Valid Parentheses",
                                "Java",
                                "Medium",
                                "Google",
                                true,
                                "Check whether brackets (), {}, [] are correctly balanced.",
                                "Input contains one bracket string.",
                                "Print true or false.",
                                "{[()]}",
                                "true",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        String s = sc.nextLine();\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (c == '(' ||
                c == '{' ||
                c == '[') {

                stack.push(c);

            } else {

                if (stack.isEmpty()) {
                    System.out.println(false);
                    return;
                }

                char top = stack.pop();

                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {

                    System.out.println(false);
                    return;
                }
            }
        }

        System.out.println(stack.isEmpty());
    }
}
""",
                                "true"
                        ),

                        coding(
                                "Binary Search",
                                "Java",
                                "Medium",
                                "Microsoft",
                                true,
                                "Find the index of a target in a sorted array. Return -1 if absent.",
                                "First line: n. Second line: sorted integers. Third line: target.",
                                "Print the index.",
                                "5\n1 3 5 7 9\n7",
                                "3",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        int low = 0;
        int high = n - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (a[mid] == target) {
                System.out.println(mid);
                return;
            }

            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(-1);
    }
}
""",
                                "3"
                        ),

                        coding(
                                "First Non-Repeating Character",
                                "Java",
                                "Medium",
                                "Amazon",
                                true,
                                "Find the first character that occurs only once.",
                                "Input contains one lowercase string.",
                                "Print the character.",
                                "swiss",
                                "w",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        String s = sc.nextLine();\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        Map<Character, Integer> freq =
                new LinkedHashMap<>();

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (char c : s.toCharArray()) {

            if (freq.get(c) == 1) {
                System.out.println(c);
                return;
            }
        }
    }
}
""",
                                "w"
                        ),

                        coding(
                                "Merge Two Sorted Arrays",
                                "Java",
                                "Medium",
                                "Infosys",
                                false,
                                "Merge two sorted arrays into one sorted array.",
                                "Read n, n integers, m and m integers.",
                                "Print merged elements.",
                                "3\n1 4 7\n3\n2 3 8",
                                "1 2 3 4 7 8",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int m = sc.nextInt();

        int[] b = new int[m];

        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }

        int i = 0;
        int j = 0;

        while (i < n && j < m) {

            if (a[i] <= b[j]) {
                System.out.print(a[i++] + " ");
            } else {
                System.out.print(b[j++] + " ");
            }
        }

        while (i < n) {
            System.out.print(a[i++] + " ");
        }

        while (j < m) {
            System.out.print(b[j++] + " ");
        }
    }
}
""",
                                "1 2 3 4 7 8"
                        ),

                        coding(
                                "Longest Increasing Subsequence",
                                "Java",
                                "Hard",
                                "Google",
                                false,
                                "Find the length of the longest strictly increasing subsequence.",
                                "First line: n. Second line: n integers.",
                                "Print the LIS length.",
                                "8\n10 9 2 5 3 7 101 18",
                                "4",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int answer = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {
                    dp[i] =
                            Math.max(dp[i], dp[j] + 1);
                }
            }

            answer =
                    Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
""",
                                "4"
                        ),

                        coding(
                                "Product of Array Except Self",
                                "Java",
                                "Hard",
                                "Amazon",
                                true,
                                "For each position print the product of all other elements.",
                                "First line: n. Second line: n integers.",
                                "Print n products.",
                                "4\n1 2 3 4",
                                "24 12 8 6",
                                "import java.util.*;\n\npublic class Main {\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n\n        // Write your solution here\n    }\n}",
                                """
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] result = new int[n];

        int prefix = 1;

        for (int i = 0; i < n; i++) {
            result[i] = prefix;
            prefix *= a[i];
        }

        int suffix = 1;

        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= a[i];
        }

        for (int x : result) {
            System.out.print(x + " ");
        }
    }
}
""",
                                "24 12 8 6"
                        ),

                        coding(
                                "Two Sum",
                                "Python",
                                "Easy",
                                "Amazon",
                                true,
                                "Find two indices whose values add up to the target.",
                                "First line n, second line array, third line target.",
                                "Print two indices.",
                                "4\n2 7 11 15\n9",
                                "0 1",
                                "# Write your solution here",
                                """
nums = list(map(int, input().split()))
target = int(input())

seen = {}

for i, value in enumerate(nums):

    needed = target - value

    if needed in seen:
        print(seen[needed], i)
        break

    seen[value] = i
""",
                                "0 1"
                        ),

                        coding(
                                "Maximum Element",
                                "Python",
                                "Medium",
                                "TCS",
                                false,
                                "Find the maximum value in an array.",
                                "First line n, second line array.",
                                "Print maximum.",
                                "5\n3 9 2 7 4",
                                "9",
                                "# Write your solution here",
                                """
nums = list(map(int, input().split()))

print(max(nums))
""",
                                "9"
                        ),

                        coding(
                                "Palindrome Number",
                                "Python",
                                "Hard",
                                "Infosys",
                                false,
                                "Check whether an integer is a palindrome.",
                                "Input contains one integer.",
                                "Print true or false.",
                                "121",
                                "true",
                                "# Write your solution here",
                                """
n = input()

print(n == n[::-1])
""",
                                "true"
                        ),

                        coding(
                                "Two Sum",
                                "C++",
                                "Easy",
                                "Amazon",
                                true,
                                "Find two indices whose values add up to the target.",
                                "First line n, second line array, third line target.",
                                "Print two indices.",
                                "4\n2 7 11 15\n9",
                                "0 1",
                                "#include <bits/stdc++.h>\nusing namespace std;\n\nint main() {\n    // Write your solution here\n    return 0;\n}",
                                """
#include <bits/stdc++.h>
using namespace std;

int main() {

    int n;
    cin >> n;

    vector<int> a(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int target;
    cin >> target;

    unordered_map<int, int> mp;

    for (int i = 0; i < n; i++) {

        int needed = target - a[i];

        if (mp.count(needed)) {
            cout << mp[needed] << " " << i;
            return 0;
        }

        mp[a[i]] = i;
    }

    return 0;
}
""",
                                "0 1"
                        ),

                        coding(
                                "Binary Search",
                                "C++",
                                "Medium",
                                "Microsoft",
                                true,
                                "Find target index in sorted array.",
                                "First line n, second line sorted array, third line target.",
                                "Print index.",
                                "5\n1 3 5 7 9\n7",
                                "3",
                                "#include <bits/stdc++.h>\nusing namespace std;\n\nint main() {\n    // Write your solution here\n    return 0;\n}",
                                """
#include <bits/stdc++.h>
using namespace std;

int main() {

    int n;
    cin >> n;

    vector<int> a(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int target;
    cin >> target;

    int low = 0;
    int high = n - 1;

    while (low <= high) {

        int mid = low + (high - low) / 2;

        if (a[mid] == target) {
            cout << mid;
            return 0;
        }

        if (a[mid] < target)
            low = mid + 1;
        else
            high = mid - 1;
    }

    cout << -1;

    return 0;
}
""",
                                "3"
                        ),

                        coding(
                                "Longest Increasing Subsequence",
                                "C++",
                                "Hard",
                                "Google",
                                false,
                                "Find the length of the longest increasing subsequence.",
                                "First line n, second line array.",
                                "Print LIS length.",
                                "8\n10 9 2 5 3 7 101 18",
                                "4",
                                "#include <bits/stdc++.h>\nusing namespace std;\n\nint main() {\n    // Write your solution here\n    return 0;\n}",
                                """
#include <bits/stdc++.h>
using namespace std;

int main() {

    int n;
    cin >> n;

    vector<int> a(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    vector<int> dp(n, 1);

    int answer = 0;

    for (int i = 0; i < n; i++) {

        for (int j = 0; j < i; j++) {

            if (a[j] < a[i]) {
                dp[i] =
                    max(dp[i], dp[j] + 1);
            }
        }

        answer =
            max(answer, dp[i]);
    }

    cout << answer;

    return 0;
}
""",
                                "4"
                        )
                ));

                System.out.println(
                        "PlaceAI: Coding problems seeded successfully."
                );
            }


            // =====================================================
            // UPDATE EXISTING CODING SOLUTIONS
            // =====================================================

            updateExistingSolutions(codingRepository);
        };
    }


    // =====================================================
    // APTITUDE HELPER
    // =====================================================

    private AwtQuestion q(
            String question,
            String a,
            String b,
            String c,
            String d,
            String correct,
            String topic,
            String difficulty,
            String solution) {

        AwtQuestion q = new AwtQuestion();

        q.setQuestion(question);
        q.setOptionA(a);
        q.setOptionB(b);
        q.setOptionC(c);
        q.setOptionD(d);
        q.setCorrectAnswer(correct);
        q.setTopic(topic);
        q.setDifficulty(difficulty);
        q.setSolution(solution);

        return q;
    }


    // =====================================================
    // CODING HELPER
    // =====================================================

    private CodingProblem coding(
            String title,
            String language,
            String difficulty,
            String company,
            boolean previouslyAsked,
            String statement,
            String inputFormat,
            String outputFormat,
            String sampleInput,
            String sampleOutput,
            String starterCode,
            String solution,
            String expectedOutput) {

        CodingProblem p = new CodingProblem();

        p.setTitle(title);
        p.setLanguage(language);
        p.setDifficulty(difficulty);
        p.setCompany(company);
        p.setPreviouslyAsked(previouslyAsked);
        p.setProblemStatement(statement);
        p.setInputFormat(inputFormat);
        p.setOutputFormat(outputFormat);
        p.setSampleInput(sampleInput);
        p.setSampleOutput(sampleOutput);
        p.setStarterCode(starterCode);
        p.setSolution(solution);
        p.setExpectedOutput(expectedOutput);

        return p;
    }


    // =====================================================
    // UPDATE EXISTING SOLUTIONS
    // =====================================================

   private void updateExistingSolutions(
        CodingProblemRepository repository) {

    List<CodingProblem> problems = repository.findAll();

    for (CodingProblem p : problems) {

        String title = p.getTitle();
        String language = p.getLanguage();

        // =====================================================
        // JAVA
        // =====================================================

        if ("Two Sum".equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int needed = target - a[i];

            if (map.containsKey(needed)) {
                System.out.println(
                        map.get(needed) + " " + i
                );
                return;
            }

            map.put(a[i], i);
        }
    }
}
""");

        }

        else if ("Reverse a String".equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        String reversed =
                new StringBuilder(s).reverse().toString();

        System.out.println(reversed);
    }
}
""");

        }

        else if ("Maximum Element".equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, sc.nextInt());
        }

        System.out.println(max);
    }
}
""");

        }

        else if ("Palindrome Number".equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int original = n;
        int reverse = 0;

        while (n != 0) {

            int digit = n % 10;

            reverse = reverse * 10 + digit;

            n /= 10;
        }

        System.out.println(original == reverse);
    }
}
""");

        }

        else if ("Count Vowels".equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine().toLowerCase();

        int count = 0;

        for (char c : s.toCharArray()) {

            if (c == 'a' ||
                c == 'e' ||
                c == 'i' ||
                c == 'o' ||
                c == 'u') {

                count++;
            }
        }

        System.out.println(count);
    }
}
""");

        }

        else if ("Valid Parentheses".equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (c == '(' ||
                c == '{' ||
                c == '[') {

                stack.push(c);

            } else {

                if (stack.isEmpty()) {
                    System.out.println(false);
                    return;
                }

                char top = stack.pop();

                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {

                    System.out.println(false);
                    return;
                }
            }
        }

        System.out.println(stack.isEmpty());
    }
}
""");

        }

        else if ("Binary Search".equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        int low = 0;
        int high = n - 1;

        while (low <= high) {

            int mid =
                    low + (high - low) / 2;

            if (a[mid] == target) {
                System.out.println(mid);
                return;
            }

            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(-1);
    }
}
""");

        }

        else if ("First Non-Repeating Character"
                .equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        Map<Character, Integer> freq =
                new LinkedHashMap<>();

        for (char c : s.toCharArray()) {

            freq.put(
                    c,
                    freq.getOrDefault(c, 0) + 1
            );
        }

        for (char c : s.toCharArray()) {

            if (freq.get(c) == 1) {

                System.out.println(c);

                return;
            }
        }
    }
}
""");

        }

        else if ("Merge Two Sorted Arrays"
                .equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int m = sc.nextInt();

        int[] b = new int[m];

        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }

        int i = 0;
        int j = 0;

        while (i < n && j < m) {

            if (a[i] <= b[j]) {
                System.out.print(a[i++] + " ");
            } else {
                System.out.print(b[j++] + " ");
            }
        }

        while (i < n) {
            System.out.print(a[i++] + " ");
        }

        while (j < m) {
            System.out.print(b[j++] + " ");
        }
    }
}
""");

        }

        else if ("Product of Array Except Self"
                .equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] result = new int[n];

        int prefix = 1;

        for (int i = 0; i < n; i++) {

            result[i] = prefix;

            prefix *= a[i];
        }

        int suffix = 1;

        for (int i = n - 1; i >= 0; i--) {

            result[i] *= suffix;

            suffix *= a[i];
        }

        for (int x : result) {
            System.out.print(x + " ");
        }
    }
}
""");

        }

        else if ("Longest Increasing Subsequence"
                .equalsIgnoreCase(title)
                && "Java".equalsIgnoreCase(language)) {

            p.setSolution("""
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int answer = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (a[j] < a[i]) {

                    dp[i] =
                            Math.max(
                                    dp[i],
                                    dp[j] + 1
                            );
                }
            }

            answer =
                    Math.max(
                            answer,
                            dp[i]
                    );
        }

        System.out.println(answer);
    }
}
""");

        }

        // =====================================================
        // PYTHON
        // =====================================================

        else if ("Two Sum".equalsIgnoreCase(title)
                && "Python".equalsIgnoreCase(language)) {

            p.setSolution("""
nums = list(map(int, input().split()))
target = int(input())

seen = {}

for i, value in enumerate(nums):

    needed = target - value

    if needed in seen:
        print(seen[needed], i)
        break

    seen[value] = i
""");

        }

        else if ("Maximum Element".equalsIgnoreCase(title)
                && "Python".equalsIgnoreCase(language)) {

            p.setSolution("""
nums = list(map(int, input().split()))

print(max(nums))
""");

        }

        else if ("Palindrome Number".equalsIgnoreCase(title)
                && "Python".equalsIgnoreCase(language)) {

            p.setSolution("""
n = input()

print(n == n[::-1])
""");

        }

        // =====================================================
        // C++
        // =====================================================

        else if ("Two Sum".equalsIgnoreCase(title)
                && "C++".equalsIgnoreCase(language)) {

            p.setSolution("""
#include <bits/stdc++.h>
using namespace std;

int main() {

    int n;
    cin >> n;

    vector<int> a(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int target;
    cin >> target;

    unordered_map<int, int> mp;

    for (int i = 0; i < n; i++) {

        int needed = target - a[i];

        if (mp.count(needed)) {

            cout << mp[needed]
                 << " "
                 << i;

            return 0;
        }

        mp[a[i]] = i;
    }

    return 0;
}
""");

        }

        else if ("Binary Search".equalsIgnoreCase(title)
                && "C++".equalsIgnoreCase(language)) {

            p.setSolution("""
#include <bits/stdc++.h>
using namespace std;

int main() {

    int n;
    cin >> n;

    vector<int> a(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int target;
    cin >> target;

    int low = 0;
    int high = n - 1;

    while (low <= high) {

        int mid =
                low + (high - low) / 2;

        if (a[mid] == target) {

            cout << mid;

            return 0;
        }

        if (a[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    cout << -1;

    return 0;
}
""");

        }

        else if ("Longest Increasing Subsequence"
                .equalsIgnoreCase(title)
                && "C++".equalsIgnoreCase(language)) {

            p.setSolution("""
#include <bits/stdc++.h>
using namespace std;

int main() {

    int n;
    cin >> n;

    vector<int> a(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    vector<int> dp(n, 1);

    int answer = 0;

    for (int i = 0; i < n; i++) {

        for (int j = 0; j < i; j++) {

            if (a[j] < a[i]) {

                dp[i] =
                    max(
                        dp[i],
                        dp[j] + 1
                    );
            }
        }

        answer =
            max(
                answer,
                dp[i]
            );
    }

    cout << answer;

    return 0;
}
""");

        }
    }

    repository.saveAll(problems);

    System.out.println(
            "PlaceAI: ALL existing coding solutions updated successfully."
    );
}
}
