package com.example.demo.mapper;

import java.util.List;

import com.example.demo.model.Product;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {

	@Select("select * from product")
	public List<Product> findAll();
}