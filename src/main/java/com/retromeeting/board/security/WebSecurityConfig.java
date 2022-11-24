package com.retromeeting.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.retromeeting.board.entity.User;
import com.retromeeting.board.service.UserService;

@Configuration
public class WebSecurityConfig {

	@Autowired
	private UserService userDetailsService ;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
			.formLogin()
			.and()
			.logout()
			.permitAll()
			.and()
			.authorizeHttpRequests().antMatchers("/**").hasRole("USER");
		
		http.authenticationProvider(authenticationProvider());
		
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().antMatchers("/h2-console/**");
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setPasswordEncoder(passwordEncoder());
		dap.setUserDetailsService(userDetailsService);
		
		return dap;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ApplicationRunner appRunner() {
		return args -> {
			userDetailsService.create(new User(null,"Anas",passwordEncoder().encode("123"),"ROLE_USER"));
			userDetailsService.create(new User(null,"Mourad",passwordEncoder().encode("123"),"ROLE_USER"));
		};
	}

}
