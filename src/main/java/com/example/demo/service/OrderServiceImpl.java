package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.payload.OrderRequest;
import com.example.demo.service.ordercheck.OrderCheck;
import com.example.demo.util.RedisUtil;
import com.google.common.util.concurrent.RateLimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private List<OrderCheck> orderCheckChain;

	/**
	 * 生成订单
	 */
	@Override
	// @Transactional(rollbackFor = Exception.class, propagation =
	// Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public void createOrder(OrderRequest orderRequest) {
		try {
			// 创建订单前的必要条件合法性检查
			for (int i = 0; i < orderCheckChain.size(); i++) {
				orderCheckChain.get(i).check(orderRequest);
			}

			// 检查库存
			Product product = checkCount(orderRequest.getProductId());
			// 库存减一
			updateCount(product);
			// 生成订单
			Order order = createOrder(product);
			// 处理结果放一份到缓存
			redisUtil.set("OrderResult::" + orderRequest.getOrderHandleId(), order);
		} catch (Exception e) {
			log.error(orderRequest.toString() + e.getMessage());
			redisUtil.set("OrderResult::" + orderRequest.getOrderHandleId(), e.getMessage());
		}

	}

	/**
	 * 检查库存
	 * 
	 * @param productId 产品id
	 * @return Product
	 * @exception RuntimeException 库存不足异常
	 */
	private Product checkCount(int productId) {

		Product product = productService.getProductById(productId).get();
		if (product.getSale() >= product.getCount()) {
			String msg = productId + "库存不足";
			throw new RuntimeException(msg);
		}

		return product;
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