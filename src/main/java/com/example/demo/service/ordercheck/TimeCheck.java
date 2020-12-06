package com.example.demo.service.ordercheck;

import com.example.demo.payload.OrderRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(3)
public class TimeCheck implements OrderCheck {

	@Override
	public void check(OrderRequest orderRequest) {

		log.info("time check");
	}

}
