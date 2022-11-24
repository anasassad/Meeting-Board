package com.retromeeting.board.service;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retromeeting.board.entity.User;
import com.retromeeting.board.repository.UserRepo;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService{
	
	private final UserRepo userRepo ;
	
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo ;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(user.getRole()))
				);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public User create(User user) {
		return userRepo.save(user);
	}

}
