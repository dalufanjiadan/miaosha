package com.example.demo.mapper;

import com.example.demo.model.Order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

	public int insert(Order order);

}
