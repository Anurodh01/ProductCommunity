package com.nagarro.productCom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.productCom.dao.ProductDao;
import com.nagarro.productCom.dao.UserDao;
import com.nagarro.productCom.entity.Product;
import com.nagarro.productCom.entity.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StatsService {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private ProductService productService;
	
	public Map<String, Integer> getStats()
	{
		//to return the stats, number of registered users and reviews and products
		Map<String, Integer> stats= new HashMap<String, Integer>();
		int users= this.userService.getUsers().size();
		stats.put("users",users);	
		
		List<Product> products = this.productService.getProducts("");
		int productCount= products.size();
		stats.put("products", productCount);
		
		int reviewCount=0;
		for(Product product : products)
		{
			Set<Review> reviews= product.getProductReviews();
//			System.out.println(reviews.size());
			for(Review review: reviews)
			{
				if(review.getIsApproved())
				{
					reviewCount += 1;
				}
			}
		}
		
		stats.put("reviews", reviewCount);		
		return stats;
	}
}
