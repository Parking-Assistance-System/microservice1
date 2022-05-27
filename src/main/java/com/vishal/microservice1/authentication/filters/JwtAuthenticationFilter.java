package com.vishal.microservice1.authentication.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vishal.microservice1.authentication.UsernamePasswordAuthentication;
import com.vishal.microservice1.jwt.JwtUtil;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


	
	
	@Autowired
    private JwtUtil jwt_util;
	
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");


        
        Claims claims;
		try {
			claims = jwt_util.verify(jwt);
			String username = String.valueOf(claims.get("username"));
			GrantedAuthority a = new SimpleGrantedAuthority("user");
	        var auth = new UsernamePasswordAuthentication(username, null, List.of(a));
	      //  var auth = new UsernamePasswordAuthentication(username, null);
	       
	        SecurityContextHolder.getContext().setAuthentication(auth);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			 throw new AccessDeniedException("Access Denied");
		}



     
     filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/login");
    }
}
