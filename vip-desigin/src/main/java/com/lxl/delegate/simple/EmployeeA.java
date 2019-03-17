package com.lxl.delegate.simple;

public class EmployeeA implements IEmployee {

	@Override
	public void doing(String command) {
		System.out.println("我是EmployeeA，我开始做" + command + "工作");

	}

}
