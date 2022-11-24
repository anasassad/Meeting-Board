package com.retromeeting.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retromeeting.board.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
