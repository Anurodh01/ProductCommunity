package com.nagarro.productCom.configuration.util;

import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY= "Ecommerce_WebSite_For_Exit_Text";
	private static final int TOKEN_VALIDITY= 3600 *5;
	public String getUserNameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	} 
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		final Claims claims= getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public boolean validateToken(String token, UserDetails userDetails)
	{
		String UserEmail= getUserNameFromToken(token);
		boolean isvalid= ( UserEmail.equals(userDetails.getUsername()) && !isTokenExpired(token) );
//		System.out.println(isvalid);
		return isvalid;
	}
	
	private boolean isTokenExpired(String token)
	{
		final Date expirationDate= getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	private Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims= new HashMap<String, Object>();
		return Jwts.builder().setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	
}
