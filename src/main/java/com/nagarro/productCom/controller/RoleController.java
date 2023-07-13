package com.nagarro.productCom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productCom.entity.Role;
import com.nagarro.productCom.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/createNewRole")
	public Role CreateNewRole(@RequestBody Role role) {
		return this.roleService.createNewRole(role);
	}
	
}
