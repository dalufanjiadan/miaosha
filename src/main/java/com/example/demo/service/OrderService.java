package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.payload.OrderRequest;

public interface OrderService {

	public Order createOrder(OrderRequest orderRequest);

}
