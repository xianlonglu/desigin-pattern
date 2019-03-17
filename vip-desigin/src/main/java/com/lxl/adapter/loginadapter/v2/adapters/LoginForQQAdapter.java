package com.lxl.adapter.loginadapter.v2.adapters;

import com.lxl.adapter.loginadapter.ResultMsg;

public class LoginForQQAdapter {
	public boolean support(Object adapter) {
		return adapter instanceof LoginForQQAdapter;
	}

	public ResultMsg login(String id, Object adapter) {
		return new ResultMsg(200, "qq", "qq登陆");
	}
}
