package com.gupaoedu.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  定义rpcPRoxyClient的类被spring加载
 */
@Configuration
public class SpringConfig {
	
	@Bean(name="rpcPRoxyClient")
	public RpcProxyClient proxyClient(){
		return new RpcProxyClient();
	}
}
