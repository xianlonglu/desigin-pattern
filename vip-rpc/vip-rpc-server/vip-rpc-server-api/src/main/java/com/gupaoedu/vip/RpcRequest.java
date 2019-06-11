package com.gupaoedu.vip;

import java.io.Serializable;

/**
 * 请求参数
 * 
 * @author Administrator
 *
 */
public class RpcRequest implements Serializable {

	private static final long serialVersionUID = -1154170440397821367L;

	/**
	 * 类名
	 */
	private String className;
	
	/**
	 * 方法名
	 */
	private String methodName;

	/**
	 * 方法参数
	 */
	private Object[] parameters;

	/**
	 * 版本号
	 */
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
