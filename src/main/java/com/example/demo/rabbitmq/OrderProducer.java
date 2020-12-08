package com.example.demo.rabbitmq;

import com.example.demo.payload.OrderRequest;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

	@Value("${rabbitmq.queue.orderQueue}")
	private String orderQueue;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void produce(OrderRequest orderRequest) {
		rabbitTemplate.convertAndSend(orderQueue, orderRequest);

	}
}