package com.example.demo.service.ordercheck;

import java.util.Optional;

import com.example.demo.model.User;
import com.example.demo.payload.OrderRequest;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(1)
public class UserCheck implements OrderCheck {

	@Autowired
	private UserService userService;

	@Override
	public void check(OrderRequest orderRequest) {

		log.info("user check");

		Optional<User> user = userService.getUserById(orderRequest.getUserId());
		if (!user.isPresent()) {
			throw new RuntimeException("创建订单失败，用户ID不合法");
		}

	}

}
