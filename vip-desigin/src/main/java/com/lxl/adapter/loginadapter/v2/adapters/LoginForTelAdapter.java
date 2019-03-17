package com.lxl.adapter.loginadapter.v2.adapters;

import com.lxl.adapter.loginadapter.ResultMsg;

public class LoginForTelAdapter {
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTelAdapter;
    }
    public ResultMsg login(String id, Object adapter) {
		return new ResultMsg(200, "Tel", "Tel登陆");
    }
}
