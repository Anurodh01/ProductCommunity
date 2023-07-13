package com.nagarro.productCom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.productCom.dao.ProductDao;
import com.nagarro.productCom.dao.ReviewDao;
import com.nagarro.productCom.entity.Product;
import com.nagarro.productCom.entity.Review;

import java.util.*;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private ProductDao productDao;
	
	public Review postReview(Review review)
	{
		return this.reviewDao.save(review);
	}
	
	public List<Review> getAllReviews()
	{
		return this.reviewDao.findAll();
	}
	
	public Review approveReview(Review review, long reviewId)
	{
		 Review unApprovedReview= this.reviewDao.findByReviewId(reviewId); 
		 unApprovedReview.setIsApproved(review.getIsApproved());
		 unApprovedReview.setAdminReviewStatus(review.isAdminReviewStatus());
		 
		 Review approvedReview = this.reviewDao.save(unApprovedReview);
		 
		 //if the review is approved then make the changes in average product rating 
		 if(approvedReview.getIsApproved())
		 {
			 Product product= approvedReview.getProduct();
			 product.updateAverageProductRating();
			 productDao.save(product);
		 }
		 
		 return approvedReview;
	}
	
	public List<Review> getNonReviewedUserReviews(boolean adminReviewStatus)
	{
		if(adminReviewStatus)
		return this.reviewDao.findByAdminReviewStatusTrue();
		
		return this.reviewDao.findByAdminReviewStatusFalse();
	}
	
	public Review getReviewByReviewId(long reviewId)
	{
		return this.reviewDao.findByReviewId(reviewId);
	}
	
}
