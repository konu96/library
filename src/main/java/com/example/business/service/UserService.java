package com.example.business.service;

import com.example.business.entity.User;

public interface UserService {
	User save(User user);
	User findOne(Long id);
}
