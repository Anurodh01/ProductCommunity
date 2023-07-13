package com.nagarro.productCom.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productCom.entity.User;
import com.nagarro.productCom.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/register")
	public User registerNewUser(@RequestBody User user)
	{
		return this.userService.createNewUser(user);
	}
	
	@PostConstruct
	public void initAdmin()
	{
		this.userService.initAdmin();
	}
	
	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('admin')")
	public String forAdmin()
	{
		return "this url is only accessible to admin";
	}
	
	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('user')")
	public String forUser() {
		return "this url is only accessible to the user";
	}
}
