package com.krunal.placeai.repository;

import com.krunal.placeai.entity.CompanyReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyReviewRepository extends JpaRepository<CompanyReview, Long> {

    List<CompanyReview> findByCompanyNameIgnoreCaseOrderByCreatedAtDesc(
            String companyName
    );

    List<CompanyReview> findAllByOrderByCreatedAtDesc();

    long countByCompanyNameIgnoreCase(
            String companyName
    );

    @Query("""
        SELECT AVG(r.rating)
        FROM CompanyReview r
        WHERE LOWER(r.companyName) = LOWER(:companyName)
    """)
    Double getAverageRatingByCompanyNameIgnoreCase(
            @Param("companyName") String companyName
    );
}