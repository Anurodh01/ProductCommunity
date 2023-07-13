package com.nagarro.productCom.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nagarro.productCom.configuration.util.JwtUtil;
import com.nagarro.productCom.service.JwtService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwtToken  = null;
		String userEmail= null;
		final String header= request.getHeader("Authorization");
//		System.out.println(header);
		if(header!= null && header.startsWith("Bearer "))
		{
			jwtToken= header.substring(7);
			
			try {
				userEmail= jwtUtil.getUserNameFromToken(jwtToken);
			}catch (IllegalArgumentException e) {
				// TODO: handle exceptions
				System.out.println("Unable to get JWT Token");
			}catch(Exception e)
			{
				System.out.println("JWT Token is expired");
			}
		}else {
			System.out.println("JWT token doesn't start with Bearer");
		}
		
		if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails= jwtService.loadUserByUsername(userEmail);
			
			if(jwtUtil.validateToken(jwtToken, userDetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

	
}
