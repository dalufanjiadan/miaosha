package com.example.demo.rabbitmq;

import com.example.demo.payload.OrderRequest;
import com.example.demo.service.OrderService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderConsumer {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "${rabbitmq.queue.orderQueue}", containerFactory = "simpleRabbitListenerContainerFactory")
    public void receivedMessage(OrderRequest orderRequest) {
        log.info("order consumer received message : " + orderRequest);
        orderService.createOrder(orderRequest);
    }
}
