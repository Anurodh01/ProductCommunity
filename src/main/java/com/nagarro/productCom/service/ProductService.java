package com.nagarro.productCom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.productCom.dao.ProductDao;
import com.nagarro.productCom.entity.Product;

import java.util.*;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public List<Product> getProducts(String searchKeywords){
		if(searchKeywords.equals(""))
		{
			return this.productDao.findAll();
		}
		return this.productDao.findByProductCodeContainingIgnoreCaseOrProductNameContainingIgnoreCaseOrProductBrandContainingIgnoreCase(searchKeywords, searchKeywords, searchKeywords);
	}
	
	public Product getProductByProductCode(String productCode) {
		return this.productDao.findById(productCode).get();
	}
	
}
