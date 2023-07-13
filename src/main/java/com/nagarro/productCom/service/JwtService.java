package com.nagarro.productCom.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.productCom.configuration.util.JwtUtil;
import com.nagarro.productCom.dao.UserDao;
import com.nagarro.productCom.entity.JwtRequest;
import com.nagarro.productCom.entity.JwtResponse;
import com.nagarro.productCom.entity.User;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userEmail = jwtRequest.getUserEmail();
		String userPassword = jwtRequest.getUserPassword();
		try {
			this.authenticate(userEmail, userPassword);
			System.out.println("authenticated...");
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Bad Credential");
		}
		final UserDetails userDetails= loadUserByUsername(userEmail);
		String newGeneratedToken= jwtUtil.generateToken(userDetails);
		User user= userDao.findById(userEmail).get();
		return new JwtResponse(user, newGeneratedToken);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= userDao.findById(username).get();
		if(user!= null)
		{
			return new org.springframework.security.core.userdetails.User(
					user.getUserEmail(),
					user.getUserPassword(),
					getAuthorities(user));
		}
		else {
			throw new UsernameNotFoundException("UserName is not valid");
		}
	}

	private Set getAuthorities(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});

		return authorities;
	}

	private void authenticate(String userEmail, String userPassword) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));
		
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("User is Diabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials from user");
		}
	}

}
