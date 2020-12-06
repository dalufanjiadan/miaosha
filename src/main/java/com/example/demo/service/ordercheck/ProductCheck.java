package com.example.demo.service.ordercheck;

import java.util.Optional;

import com.example.demo.model.Product;
import com.example.demo.payload.OrderRequest;
import com.example.demo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(2)
public class ProductCheck implements OrderCheck {

	@Autowired
	private ProductService productService;

	/**
	 * 检查商品id 是否合法
	 */
	@Override
	public void check(OrderRequest orderRequest) {

		log.info("product check");
		Optional<Product> product = productService.getProductById(orderRequest.getProductId());
		if(!product.isPresent()){
			throw new RuntimeException("创建订单失败，商品ID不合法");
		}
	}

}
