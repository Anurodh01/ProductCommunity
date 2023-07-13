package com.nagarro.productCom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productCom.entity.JwtRequest;
import com.nagarro.productCom.entity.JwtResponse;
import com.nagarro.productCom.service.JwtService;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/user/authenticate")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtService.createJwtToken(jwtRequest);
	}
}
