package com.example.demo.service.ordercheck;

import com.example.demo.payload.OrderRequest;
import com.example.demo.util.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(4)
public class FrequencyCheck implements OrderCheck {

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void check(OrderRequest orderRequest) {

		log.info("frequency check");

		// redis 频率限制key
		String limitKey = "miaosha_" + orderRequest.getUserId() + "_" + orderRequest.getProductId();

		Object value = redisUtil.get(limitKey);
		if (value != null) {
			if ((int) value > 10) {
				throw new RuntimeException("购买失败，超过频率限制");
			}
			redisUtil.incr(limitKey, 1);
		} else {
			redisUtil.set(limitKey, 1, 60);
		}

	}

}
