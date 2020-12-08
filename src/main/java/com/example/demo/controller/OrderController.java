package com.example.demo.controller;

import com.example.demo.model.RestResponse;
import com.example.demo.payload.OrderRequest;
import com.example.demo.rabbitmq.OrderProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderProducer orderProducer;


	@PostMapping
	public RestResponse<Object> createOrder(@RequestBody OrderRequest orderRequest) {
		// 参数校验
		// todo

		orderProducer.produce(orderRequest);
		return null;
	}

}
