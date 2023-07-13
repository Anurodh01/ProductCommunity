package com.nagarro.productCom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long reviewId;
	private String reviewHeading;
	private String reviewMessage;
	private String reviewDate;
	private double reviewRating;
	private boolean isApproved;
	private boolean adminReviewStatus;

	
	@ManyToOne()
	@JoinColumn(name="product_id")
	@JsonIgnoreProperties("productReviews")
	private Product product;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public boolean getIsApproved() {
		return isApproved;
	}
	

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public double getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(double reviewRating) {
		this.reviewRating = reviewRating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewMessage() {
		return reviewMessage;
	}

	public void setReviewMessage(String reviewMessage) {
		this.reviewMessage = reviewMessage;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Product getProduct()
	{
		return this.product;
	}
	
	public void setProduct(Product product) {
		this.product= product;
	}

	public Review(long reviewId, String reviewMessage, String reviewDate, Product product) {
		super();
		this.reviewId = reviewId;
		this.reviewMessage = reviewMessage;
		this.reviewDate = reviewDate;
		this.product = product;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}


	public boolean isAdminReviewStatus() {
		return adminReviewStatus;
	}


	public void setAdminReviewStatus(boolean adminReviewStatus) {
		this.adminReviewStatus = adminReviewStatus;
	}


	public String getReviewHeading() {
		return reviewHeading;
	}


	public void setReviewHeading(String reviewHeading) {
		this.reviewHeading = reviewHeading;
	}

	
	
	
}
