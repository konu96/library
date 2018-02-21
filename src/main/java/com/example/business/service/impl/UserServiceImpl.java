package com.example.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.business.entity.User;
import com.example.business.repository.UserRepository;
import com.example.business.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
}
