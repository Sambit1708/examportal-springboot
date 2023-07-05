package com.exam.portal.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.config.JwtUtil;
import com.exam.portal.entites.JwtRequest;
import com.exam.portal.entites.JwtResponse;
import com.exam.portal.entites.User;
import com.exam.portal.services.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch (UsernameNotFoundException e)	{
			System.out.println("Username not found "+e.getMessage());
			throw new Exception("User not found.");
		}
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			System.out.println("User Disabled "+e.getMessage());
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credintials "+e.getMessage());
		}
	}
	
	// Return the details for current user.
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		
		User currUser = (User) this.userDetailsService.loadUserByUsername(principal.getName());
		return currUser;
	}
}
