package com.nagarro.productCom.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.productCom.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, String> {
	public List<Product> findByProductCodeContainingIgnoreCaseOrProductNameContainingIgnoreCaseOrProductBrandContainingIgnoreCase(String key1, String key2, String key3);
}
