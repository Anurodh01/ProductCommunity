package com.nagarro.productCom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="image_model")
public class ImageModel {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long imageId;
	private String imageName;
		
	@ManyToOne
	@JoinColumn(name= "product_id")
	@JsonBackReference
	private Product product;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ImageModel(long imageId, String imageName, Product product) {
		super();
		this.imageId = imageId;
		this.imageName = imageName;
		this.product = product;
	}

	public ImageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
