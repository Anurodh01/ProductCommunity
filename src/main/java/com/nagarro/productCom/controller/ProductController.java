package com.nagarro.productCom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productCom.entity.*;
import com.nagarro.productCom.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/products")
	@PreAuthorize("hasAnyRole('user','admin')")
	public List<Product> getProducts(@RequestParam(required = false) String searchKeywords)
	{
		return this.productService.getProducts(searchKeywords);
	}
	
	
	@GetMapping("/products/{productCode}")
	@PreAuthorize("hasAnyRole('user','admin')")
	public Product getProductByProductCode(@PathVariable("productCode") String productCode)
	{
		return productService.getProductByProductCode(productCode);
	}
}
