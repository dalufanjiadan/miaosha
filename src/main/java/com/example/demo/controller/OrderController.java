package com.example.demo.controller;

import com.example.demo.payload.OrderRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@PostMapping
	public void createOrder(@RequestBody OrderRequest orderRequest) {

		System.out.println(orderRequest);
	}

}
