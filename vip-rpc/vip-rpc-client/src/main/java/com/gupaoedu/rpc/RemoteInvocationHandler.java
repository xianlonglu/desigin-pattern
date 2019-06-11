package com.gupaoedu.rpc;

import com.gupaoedu.vip.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

/**
 * 执行器
 */
public class RemoteInvocationHandler implements InvocationHandler {

	private String host;
	private int port;
	private String version;

	public RemoteInvocationHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public RemoteInvocationHandler(String host, int port, String version) {
		this.host = host;
		this.port = port;
		this.version = version;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 请求会进入到这里
		System.out.println("come in RemoteInvocationHandler");
		// 请求数据的包装
		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setClassName(method.getDeclaringClass().getName());
		rpcRequest.setMethodName(method.getName());
			rpcRequest.setParameters(args);
		if (!StringUtils.isEmpty(version)) {
			rpcRequest.setVersion(version);
		}
		// 远程通信
		RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
		return rpcNetTransport.send(rpcRequest);
	}

}
