package com.exam.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class MySecurityConfig {
	
	
	@Autowired
	private JwtAuthenticationEntryPoint unAuthorisedHandler;
	
	@Autowired
	private JwtAuthenticatorFilter jwtAuthenticatorFilter;
	
	@Bean
	AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable())
			.authorizeHttpRequests(auth -> 
				auth.requestMatchers("/generate-token","/user/create-user", "/v3/api-docs").permitAll()
					.requestMatchers(HttpMethod.OPTIONS).permitAll()
					.anyRequest().authenticated()
			)
			.sessionManagement(session -> 
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(exception -> exception.authenticationEntryPoint(unAuthorisedHandler));
			
		
		http.addFilterBefore(jwtAuthenticatorFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
}
