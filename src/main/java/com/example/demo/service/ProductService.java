package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;

	public List<Product> getProducts() {
		return productMapper.findAll();
	}

	public Optional<Product> getProductById(int productId) {

		return productMapper.findById(productId);
	}

	public void updateProduct(Product product) {

		productMapper.update(product);
	}

}
