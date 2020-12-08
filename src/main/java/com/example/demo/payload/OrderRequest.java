package com.example.demo.payload;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {

	private String orderHandleId;


	@NotNull(message = "商品ID不能为空")
	@JsonProperty("product_id")
	private Integer productId;

	@NotNull(message = "用户ID不能为空")
	@JsonProperty("user_id")
	private Integer userId;
}
