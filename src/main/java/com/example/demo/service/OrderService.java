package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.payload.OrderRequest;

import org.springframework.scheduling.annotation.Async;

public interface OrderService {

	@Async
	public void createOrder(OrderRequest orderRequest);

}
