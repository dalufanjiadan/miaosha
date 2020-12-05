package com.example.demo.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.payload.OrderRequest;
import com.google.common.util.concurrent.RateLimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * OrderServiceImpl
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderMapper orderMapper;

	// 每秒放行10个请求
	RateLimiter rateLimiter = RateLimiter.create(10);

	/**
	 * 令牌桶限流
	 * 
	 * 生成订单
	 */
	@Override
	// @Transactional(rollbackFor = Exception.class, propagation =
	// Propagation.REQUIRED,isolation = Isolation.SERIALIZABLE)
	public Order createOrder(OrderRequest orderRequest) {

		// 阻塞式获取令牌
		// log.info("等待时间" + rateLimiter.acquire());
		// 非阻塞式获取令牌
		// if (!rateLimiter.tryAcquire(1000 + RandomUtil.randomInt(1000, 10000), TimeUnit.MILLISECONDS)) {
		// 	log.warn("限流了，直接返回失败");
		// 	throw new RuntimeException("购买失败，库存不足");
		// }

		synchronized (this) {
			// 检查库存
			Product product = checkCount(orderRequest.getProductId());
			// 库存减一
			updateCount(product);
			// 生成订单
			return createOrder(product);
		}
	}

	/**
	 * 检查库存
	 * 
	 * @param productId 产品id
	 * @return Product
	 * @exception RuntimeException 库存不足异常
	 * @exception RuntimeException 错误productId异常
	 */
	private Product checkCount(int productId) {

		Optional<Product> product = productService.getProductById(productId);
		if (product.isPresent()) {
			Product p = product.get();
			if (p.getSale() >= p.getCount()) {
				// 库存不足异常
				String msg = productId + "库存不足";
				// log.error(msg);
				throw new RuntimeException(msg);
			}
		} else {
			// 找不到product异常
			String msg = productId + "产品ID错误";
			// log.error(msg);
			throw new RuntimeException(msg);
		}
		return product.get();
	}

	// 更新库存
	private void updateCount(Product product) {

		int rowAffected = productService.updateByPessimisticLock(product);

		if (rowAffected == 0) {
			throw new RuntimeException("并发更新失败，乐观锁version不匹配");
		}

	}

	public Order createOrder(Product product) {

		Order order = new Order();
		order.setProductId(product.getId());

		orderMapper.insert(order);

		String msg = "生成订单：" + order.toString();
		log.info(msg);
		return order;
	}

}