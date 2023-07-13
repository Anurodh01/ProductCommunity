package com.nagarro.productCom.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	private String productCode;
	private String productName;
	private String productBrand;
	private String productDescription;
	private double productRating;
	private double productPrice;
	
	@OneToMany(mappedBy= "product", fetch= FetchType.EAGER)
	private Set<Review> productReviews;
	
	@OneToMany(mappedBy="product", fetch= FetchType.EAGER)
	private Set<ImageModel> productImages;
	
	public Set<ImageModel> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ImageModel> productImages) {
		this.productImages = productImages;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public double getProductRating() {
		return productRating;
	}

	public void setProductRating(double productRating) {
		this.productRating = productRating;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public Set<Review> getProductReviews() {
		return productReviews;
	}

	public void setProductReviews(Set<Review> productReviews) {
		this.productReviews = productReviews;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Product(String productCode, String productName, String productBrand, double productRating,
			double productPrice, Set<Review> productReviews, Set<ImageModel> productImages) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productRating = productRating;
		this.productPrice = productPrice;
		this.productReviews = productReviews;
		this.productImages = productImages;
	}


	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void updateAverageProductRating()
	{
		int reviewCount=0;
		int totalReviewRating= 0;
		for(Review review: productReviews)
		{
			if(review.getIsApproved())
			{
			totalReviewRating += review.getReviewRating();
			reviewCount += 1;
			}
		}
		this.productRating= (double)totalReviewRating/(double)reviewCount;
		
	}
	
	
}
