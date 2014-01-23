package com.makble.springmvcstart.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.makble.springmvcstart.config.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findUsersByUsernameAndPassword(String u, String p);
	User findByUsername(String username );
}