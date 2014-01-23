package com.makble.springmvcstart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makble.springmvcstart.config.entity.User;
import com.makble.springmvcstart.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userDao;
	
	public boolean login( String  username, String password ) {
		List<User > u = userDao.findUsersByUsernameAndPassword(username, password);
		
		if ( u.size() == 1 ) return true;
		else return false;
		
	}
}