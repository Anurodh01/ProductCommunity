package com.nagarro.productCom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.productCom.entity.Review;

public interface ReviewDao extends JpaRepository<Review, Integer> {
	public Review findByReviewId(long key);
	public List<Review> findByIsApprovedFalse();
	public List<Review> findByAdminReviewStatusFalse();
	public List<Review> findByAdminReviewStatusTrue();
}
