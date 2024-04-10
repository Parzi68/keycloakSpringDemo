package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JWTAuthConverter jwtAuthConverter = new JWTAuthConverter();
	
	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.authorizeHttpRequests(req -> {
			req.requestMatchers("/api/v1/demo", "/api/v1/demo/key").authenticated();
			req.requestMatchers("/api/v1/demo/public").permitAll();
		});
		
		httpSecurity.oauth2ResourceServer(token -> {
			token.jwt( jwt -> {
				jwt.jwtAuthenticationConverter(jwtAuthConverter);
			});
		});
		
		httpSecurity.sessionManagement(ses -> {
			ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				});
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return httpSecurity.build();
	}
}
