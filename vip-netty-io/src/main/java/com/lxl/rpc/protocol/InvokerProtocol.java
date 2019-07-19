package com.lxl.rpc.protocol;

import lombok.Data;
import java.io.Serializable;

/**
 * 自定义传输协议
 */
public class InvokerProtocol implements Serializable {

    private String className;//类名
    private String methodName;//函数名称 
    private Class<?>[] parames;//形参列表
    private Object[] values;//实参列表
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
	public Class<?>[] getParames() {
		return parames;
	}
	public void setParames(Class<?>[] parames) {
		this.parames = parames;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}

}
