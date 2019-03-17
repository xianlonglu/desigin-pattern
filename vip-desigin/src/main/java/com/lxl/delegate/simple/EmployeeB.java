package com.lxl.delegate.simple;

public class EmployeeB implements IEmployee {

	@Override
	public void doing(String command) {
		System.out.println("我是EmployeeB，我开始做" + command + "工作");

	}

}
