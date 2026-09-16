package com.krunal.placeai.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krunal.placeai.dto.CompanyReviewRequest;
import com.krunal.placeai.entity.CompanyReview;
import com.krunal.placeai.entity.User;
import com.krunal.placeai.repository.CompanyReviewRepository;
import com.krunal.placeai.repository.UserRepository;

@RestController
@RequestMapping("/api/company-reviews")
@CrossOrigin
public class CompanyReviewController {

    @Autowired
    private CompanyReviewRepository companyReviewRepository;

    @Autowired
    private UserRepository userRepository;


    // =========================================================
    // POST REVIEW
    // =========================================================

    @PostMapping
    public ResponseEntity<?> addReview(
            @RequestBody CompanyReviewRequest request
    ) {

        try {

            // -------------------------
            // VALIDATE EMAIL
            // -------------------------

            if (request.getEmail() == null ||
                    request.getEmail().trim().isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body("Email is required.");
            }


            // -------------------------
            // VALIDATE COMPANY
            // -------------------------

            if (request.getCompanyName() == null ||
                    request.getCompanyName().trim().isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body("Company name is required.");
            }


            // -------------------------
            // VALIDATE RATING
            // -------------------------

            if (request.getRating() < 1 ||
                    request.getRating() > 5) {

                return ResponseEntity
                        .badRequest()
                        .body("Rating must be between 1 and 5.");
            }


            // -------------------------
            // VALIDATE COMMENT
            // -------------------------

            if (request.getComment() == null ||
                    request.getComment().trim().isEmpty()) {

                return ResponseEntity
                        .badRequest()
                        .body("Comment is required.");
            }


            // -------------------------
            // FIND USER
            // -------------------------

            User user =
                    userRepository
                            .findByEmail(request.getEmail().trim())
                            .orElseThrow(
                                    () -> new RuntimeException(
                                            "User not found."
                                    )
                            );


            // -------------------------
            // CREATE REVIEW
            // -------------------------

            CompanyReview review =
                    new CompanyReview();


            review.setCompanyName(
                    request.getCompanyName().trim()
            );


            review.setRating(
                    request.getRating()
            );


            review.setComment(
                    request.getComment().trim()
            );


            review.setUser(
                    user
            );


            // -------------------------
            // IMPORTANT
            // created_at NULL ERROR FIX
            // -------------------------

            review.setCreatedAt(
                    LocalDateTime.now()
            );


            // -------------------------
            // SAVE TO DATABASE
            // -------------------------

            CompanyReview savedReview =
                    companyReviewRepository.save(
                            review
                    );


            // -------------------------
            // RESPONSE
            // -------------------------

            Map<String, Object> response =
                    new HashMap<>();


            response.put(
                    "id",
                    savedReview.getId()
            );


            response.put(
                    "companyName",
                    savedReview.getCompanyName()
            );


            response.put(
                    "rating",
                    savedReview.getRating()
            );


            response.put(
                    "comment",
                    savedReview.getComment()
            );


            response.put(
                    "userName",
                    user.getFullName()
            );


            response.put(
                    "createdAt",
                    savedReview.getCreatedAt()
            );


            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);

        }

        catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }



    // =========================================================
    // GET ALL REVIEWS
    // =========================================================

    @GetMapping("/all")
    public ResponseEntity<?> getAllReviews() {

        try {

            List<CompanyReview> reviews =
                    companyReviewRepository
                            .findAllByOrderByCreatedAtDesc();


            List<Map<String, Object>> response =
                    new ArrayList<>();


            for (CompanyReview review : reviews) {

                Map<String, Object> item =
                        new HashMap<>();


                item.put(
                        "id",
                        review.getId()
                );


                item.put(
                        "companyName",
                        review.getCompanyName()
                );


                item.put(
                        "rating",
                        review.getRating()
                );


                item.put(
                        "comment",
                        review.getComment()
                );


                // -------------------------
                // USER NAME
                // -------------------------

                if (review.getUser() != null) {

                    item.put(
                            "userName",
                            review.getUser().getFullName()
                    );

                }

                else {

                    item.put(
                            "userName",
                            "Anonymous"
                    );
                }


                // -------------------------
                // CREATED AT
                // -------------------------

                item.put(
                        "createdAt",
                        review.getCreatedAt()
                );


                response.add(
                        item
                );
            }


            return ResponseEntity.ok(
                    response
            );

        }

        catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .status(
                            HttpStatus.INTERNAL_SERVER_ERROR
                    )
                    .body(
                            "Unable to load reviews."
                    );
        }
    }



    // =========================================================
    // GET REVIEWS BY COMPANY
    // =========================================================

    @GetMapping
    public ResponseEntity<?> getReviews(
            @RequestParam String company
    ) {

        try {

            List<CompanyReview> reviews =
                    companyReviewRepository
                            .findByCompanyNameIgnoreCaseOrderByCreatedAtDesc(
                                    company
                            );


            List<Map<String, Object>> response =
                    new ArrayList<>();


            for (CompanyReview review : reviews) {

                Map<String, Object> item =
                        new HashMap<>();


                item.put(
                        "id",
                        review.getId()
                );


                item.put(
                        "companyName",
                        review.getCompanyName()
                );


                item.put(
                        "rating",
                        review.getRating()
                );


                item.put(
                        "comment",
                        review.getComment()
                );


                if (review.getUser() != null) {

                    item.put(
                            "userName",
                            review.getUser().getFullName()
                    );

                }

                else {

                    item.put(
                            "userName",
                            "Anonymous"
                    );
                }


                item.put(
                        "createdAt",
                        review.getCreatedAt()
                );


                response.add(
                        item
                );
            }


            return ResponseEntity.ok(
                    response
            );

        }

        catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .status(
                            HttpStatus.INTERNAL_SERVER_ERROR
                    )
                    .body(
                            "Unable to load reviews."
                    );
        }
    }



    // =========================================================
    // COMPANY SUMMARY
    // =========================================================

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary(
            @RequestParam String company
    ) {

        try {

            long count =
                    companyReviewRepository
                            .countByCompanyNameIgnoreCase(
                                    company
                            );


            Double average =
                    companyReviewRepository
                            .getAverageRatingByCompanyNameIgnoreCase(
                                    company
                            );


            Map<String, Object> response =
                    new HashMap<>();


            response.put(
                    "companyName",
                    company
            );


            response.put(
                    "reviewCount",
                    count
            );


            response.put(
                    "averageRating",
                    average == null
                            ? 0
                            : average
            );


            return ResponseEntity.ok(
                    response
            );

        }

        catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity
                    .status(
                            HttpStatus.INTERNAL_SERVER_ERROR
                    )
                    .body(
                            "Unable to load summary."
                    );
        }
    }

}