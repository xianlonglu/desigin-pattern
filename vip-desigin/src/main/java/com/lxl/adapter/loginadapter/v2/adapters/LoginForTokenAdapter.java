package com.lxl.adapter.loginadapter.v2.adapters;

import com.lxl.adapter.loginadapter.ResultMsg;

public class LoginForTokenAdapter {
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
