package com.example.demo.mapper;

import java.util.Optional;

import com.example.demo.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@Mapper
@CacheConfig(cacheNames = "user")
public interface UserMapper {

	@Cacheable(key = "#p0")
	public Optional<User> findById(int userId);
}