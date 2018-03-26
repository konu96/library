package com.example.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.business.entity.User;
import com.example.business.repository.UserRepository;
import com.example.business.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User save(User user) {
		user.setPassword( passwordEncoder.encode( user.getPassword() ) );
		user = userRepository.save( user );
		
		return user;
	}
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
}
