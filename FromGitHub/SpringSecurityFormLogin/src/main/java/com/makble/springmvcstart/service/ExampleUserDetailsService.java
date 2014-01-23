package com.makble.springmvcstart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.makble.springmvcstart.config.entity.Role;
import com.makble.springmvcstart.config.entity.User;
import com.makble.springmvcstart.repository.UserRepository;

@Service
public class ExampleUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userDao ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userDao.findByUsername(username);
		List<Role> roles = u.getRoles();		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for( Role r : roles ) {
			grantedAuthorities.add(new SimpleGrantedAuthority(r.getRole()));
		}
		
		return new org.springframework.security.core.userdetails.User(
				u.getUsername(),
				u.getPassword(),
				true,
				true,
				true,
				true,
				grantedAuthorities
				);
	}

}
