package com.example.demo.service;

import java.util.Optional;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public Optional<User> getUserById(int userId) {

		return userMapper.findById(userId);

	}

}
