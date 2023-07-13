package com.nagarro.productCom.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.productCom.dao.RoleDao;
import com.nagarro.productCom.dao.UserDao;
import com.nagarro.productCom.entity.Role;
import com.nagarro.productCom.entity.User;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User createNewUser(User user)
	{
		
		//by default User role will be assigned
		Role userRole = new Role();
		userRole.setRoleName("user");
		userRole.setRoleDescription("user Role");
		roleDao.save(userRole);
		Set<Role> userRoles= new HashSet<>();
		userRoles.add(userRole);
		user.setRole(userRoles);
		user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		return this.userDao.save(user);
	}
	
	public List<User> getUsers() {
		
		List<User> allUsers;
		allUsers = this.userDao.findAll();
		List<User> users= new ArrayList<>();
		
		for(User user: allUsers)
		{
			for(Role role: user.getRole())
			{
				if(role.getRoleName().equals("user"))
				{
					users.add(user);
				}
			}
		}
		return users;
	}
	
	public void initAdmin() {
		Role adminRole= new Role();
		adminRole.setRoleName("admin");
		adminRole.setRoleDescription("admin Role");
		roleDao.save(adminRole);
		
		User adminUser= new User();
		adminUser.setUserFirstName("anurodh");
		adminUser.setUserLastName("singh");
		adminUser.setUserEmail("anurodhsinghmp@gmail.com");
		adminUser.setUserPassword(getEncodedPassword("anurodh@123"));   //password is anurodh@123
		Set<Role> adminRoles= new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userDao.save(adminUser);
		
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
}
