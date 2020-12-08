package com.example.demo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {

	private String orderHandleId;

	@JsonProperty("product_id")
	private Integer productId;

	@JsonProperty("user_id")
	private Integer userId;
}
