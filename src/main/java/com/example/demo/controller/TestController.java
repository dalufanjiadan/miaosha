package com.example.demo.controller;

import com.example.demo.mapper.TestMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestMapper testMapper;

	@GetMapping("/hello")
	public void hello() {

		System.out.println(testMapper.findAll());
	}
}
