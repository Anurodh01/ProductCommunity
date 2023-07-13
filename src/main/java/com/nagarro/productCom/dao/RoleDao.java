package com.nagarro.productCom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.productCom.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, String>{
	
}
