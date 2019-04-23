package com.gupao.vip.mic.dubbo.order;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.container.Main;

/**
 * Hello world!
 *
 */
public class ProviderApp {
	public static void main(String[] args) throws IOException {
		// 启动方式1
		// ClassPathXmlApplicationContext context = new
		// ClassPathXmlApplicationContext(
		// "META-INF/spring/order-provider.xml.xml");
		// context.start();
		// System.in.read(); // 阻塞当前进程

		// 启动方式2, 启动多个容器
		// Main.main(new String[]{"Spring", "log4j"});

		// 启动方式3
		Main.main(args);// Main.main(new String[]{"Spring"});// 等价
	}
}
