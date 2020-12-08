package com.example.demo.rabbitmq;

import java.util.concurrent.TimeUnit;

import com.example.demo.payload.OrderRequest;
import com.example.demo.service.OrderService;
import com.google.common.util.concurrent.RateLimiter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderConsumer {

    @Autowired
    private OrderService orderService;

    // 每秒10个令牌
    RateLimiter rateLimiter = RateLimiter.create(100);

    @RabbitListener(queues = "${rabbitmq.queue.orderQueue}", containerFactory = "simpleRabbitListenerContainerFactory")
    public void receivedMessage(OrderRequest orderRequest) {

        log.info("订单消费者收到消息 : " + orderRequest);
        // 阻塞式获取令牌
        log.info("获取令牌等待时间 : " + rateLimiter.acquire());
        // 非阻塞式获取令牌
        // if (!rateLimiter.tryAcquire(1000 + RandomUtil.randomInt(1000, 10000),
        // TimeUnit.MILLISECONDS)) {
        // log.warn("限流了，直接返回失败");
        // throw new RuntimeException("购买失败，库存不足");
        // }

        // 异步多线程处理
        orderService.createOrder(orderRequest);
    }
}
