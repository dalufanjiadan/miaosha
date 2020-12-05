package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.RestResponse;
import com.example.demo.payload.OrderRequest;
import com.example.demo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public RestResponse<Object> createOrder(@RequestBody OrderRequest orderRequest) {

		return RestResponse.ok(orderService.createOrder(orderRequest));
	}

}
