package com.example.demo.config;

import java.util.concurrent.Executor;

import com.example.demo.exception.GlobalAsyncExceptionHandler;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

	@Autowired
	private GlobalAsyncExceptionHandler exceptionHandler;

	@Override
	public Executor getAsyncExecutor() {
		// 定义线程池
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		// 设置核心线程数
		threadPoolTaskExecutor.setCorePoolSize(10);
		// 设置线程池最大线程数
		threadPoolTaskExecutor.setMaxPoolSize(100);
		// 设置线程队列最大线程数
		threadPoolTaskExecutor.setQueueCapacity(3000);
		// 初始化线程池
		threadPoolTaskExecutor.initialize();

		return threadPoolTaskExecutor;

	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return exceptionHandler;
	}

}