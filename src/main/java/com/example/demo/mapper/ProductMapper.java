package com.example.demo.mapper;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Product;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@Mapper
@CacheConfig(cacheNames = "product")
public interface ProductMapper {

	@Select("select * from product")
	public List<Product> findAll();

	// @Cacheable(key = "#p0")
	public Optional<Product> findById(int productId);

	public int update(Product product);

	public int updateByPessimisticLock(Product product);

}