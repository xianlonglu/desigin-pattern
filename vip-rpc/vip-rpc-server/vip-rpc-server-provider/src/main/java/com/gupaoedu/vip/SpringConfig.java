package com.gupaoedu.vip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扫描 有注解的类
 * 
 * 定义gpRpcServer的类被spring加载
 */

@Configuration
@ComponentScan(basePackages = "com.gupaoedu.vip")
public class SpringConfig {

	@Bean(name = "gpRpcServer")
	public GpRpcServer gpRpcServer() {
		return new GpRpcServer(8080);
	}
}
