package com.example.demo.mapper;

import java.util.Optional;

import com.example.demo.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	public Optional<User> findById(int userId);
}