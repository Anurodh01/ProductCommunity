package com.nagarro.productCom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.productCom.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

}
