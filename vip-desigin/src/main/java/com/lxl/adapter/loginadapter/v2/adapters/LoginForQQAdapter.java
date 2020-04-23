package com.lxl.adapter.loginadapter.v2.adapters;

import com.lxl.adapter.loginadapter.ResultMsg;
import com.lxl.adapter.loginadapter.v1.service.SiginService;

public class LoginForQQAdapter extends SiginService {
	public boolean support(Object adapter) {
		return adapter instanceof LoginForQQAdapter;
	}

	public ResultMsg login(String id, Object adapter) {
		// return new ResultMsg(200, "qq", "qq登陆");
		if (!support(adapter)) {
			return null;
		}
		// accesseToken
		// time
		return loginForRegist(id, null);
	}

	public ResultMsg loginForRegist(String username, String passport) {
		super.regist(username, passport);
		return super.login(username, passport);
	}
}
