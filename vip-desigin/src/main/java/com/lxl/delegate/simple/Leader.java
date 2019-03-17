package com.lxl.delegate.simple;

import java.util.HashMap;
import java.util.Map;

public class Leader implements IEmployee {

	private static Map<String, IEmployee> register = new HashMap<String, IEmployee>();

	static {
		register.put("登陆", new EmployeeA());
		register.put("加密", new EmployeeB());
	}
	@Override
	public void doing(String command) {
	    //项目经理自己不干活
		register.get(command).doing(command);
	}

}
