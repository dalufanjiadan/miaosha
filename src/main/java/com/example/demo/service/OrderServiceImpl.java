package com.example.demo.service;

import java.util.Optional;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.payload.OrderRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderServiceImpl
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public Order createOrder(OrderRequest orderRequest) {

		// 检查库存
		Product product = checkCount(orderRequest.getProductId());
		// 库存减一
		updateCount(product);
		// 生成订单
		return createOrder(product);
	}

	// 检查库存
	private Product checkCount(int productId) {

		Optional<Product> product = productService.getProductById(productId);
		if (product.isPresent()) {
			Product p = product.get();
			if (p.getSale() >= p.getCount()) {
				// 库存不足异常
			}
		} else {
			// 找不到product异常
		}
		return product.get();
	}

	// 更新库存
	private void updateCount(Product product) {

		product.setSale(product.getSale() + 1);
		productService.updateProduct(product);
	}

	public Order createOrder(Product product) {

		Order order = new Order();
		order.setProductId(product.getId());

		orderMapper.insert(order);

		return order;
	}

}