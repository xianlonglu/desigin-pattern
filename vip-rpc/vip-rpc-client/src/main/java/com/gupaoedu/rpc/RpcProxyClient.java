package com.gupaoedu.rpc;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class RpcProxyClient {

	public <T> T clientProxy(final Class<T> interfaceClass, final String host, final int port) {

		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
				new Class<?>[] { interfaceClass }, new RemoteInvocationHandler(host, port));
	}

	public <T> T clientProxy(final Class<T> interfaceClass, final String host, final int port,
			final String version) {
		return (T) Proxy
				.newProxyInstance(interfaceClass.getClassLoader(),
						new Class<?>[] { interfaceClass }, new RemoteInvocationHandler(host, port,
								version));
	}
}
