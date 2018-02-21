package com.example.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.business.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}
