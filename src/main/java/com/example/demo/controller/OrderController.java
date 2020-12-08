package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.model.RestResponse;
import com.example.demo.payload.OrderRequest;
import com.example.demo.rabbitmq.OrderProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.util.IdUtil;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderProducer orderProducer;


	@PostMapping
	public RestResponse<Object> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
		
		// 生成订单处理编号、前端用此编号轮询订单处理状态
		String simpleUUID = IdUtil.simpleUUID();
		orderRequest.setOrderHandleId(simpleUUID);
		orderProducer.produce(orderRequest);

		return RestResponse.ok(simpleUUID);
	}

}
