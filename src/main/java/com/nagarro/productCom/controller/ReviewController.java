package com.nagarro.productCom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productCom.entity.Review;
import com.nagarro.productCom.service.ReviewService;

import java.util.List;

@RestController
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService; 
	
	@PostMapping("/reviews")
	@PreAuthorize("hasAnyRole('user','admin')")
	public  Review postReview(@RequestBody Review review) {
		return this.reviewService.postReview(review);
	}
	
	@GetMapping("/reviews")
	@PreAuthorize("hasRole('admin')")
	public List<Review> getAllReviews(){
		return this.reviewService.getAllReviews();
	}
	
	@GetMapping("/reviews/status")
	public List<Review> getNonReviewedUserReviews(@RequestParam("adminReviewStatus") boolean adminReviewStatus)
	{
		return this.reviewService.getNonReviewedUserReviews(adminReviewStatus);
	}
	
	@PreAuthorize("hasAnyRole('user','admin')")
	@GetMapping("/reviews/{reviewId}")
	public Review getReviewByReviewId(@PathVariable("reviewId") long reviewId) {
		return this.reviewService.getReviewByReviewId(reviewId);
	}
	
	@PreAuthorize("hasRole('admin')")
	@PutMapping("/approveReview/{reviewId}")
	public Review approveReview(@RequestBody Review review,@PathVariable("reviewId") long reviewId) {
		return this.reviewService.approveReview(review, reviewId);
	}
	
}
