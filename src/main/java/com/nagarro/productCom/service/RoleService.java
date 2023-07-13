package com.nagarro.productCom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.productCom.dao.RoleDao;
import com.nagarro.productCom.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	public Role createNewRole(Role role)
	{
		return this.roleDao.save(role);
	}
	
}
