package com.gupaoedu.rpc;

import com.gupaoedu.vip.IHelloService;
import com.gupaoedu.vip.IPaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// RpcProxyClient rpcProxyClient = new RpcProxyClient();
		// IHelloService iHelloService =
		// rpcProxyClient.clientProxy(IHelloService.class,
		// "localhost", 8080, "v2.0");
		// System.out.println(iHelloService.sayHello("Mic"));

		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		RpcProxyClient rpcProxyClient = context.getBean(RpcProxyClient.class);

		IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost",
				8080, "v1.0");
		System.out.println(iHelloService.sayHello("lxl"));

		IPaymentService iPaymentService = rpcProxyClient.clientProxy(IPaymentService.class,
				"localhost", 8080);

		System.out.println(iPaymentService.doPay());
	}
}
