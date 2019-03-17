package com.lxl.adapter.loginadapter.v2.adapters;

import com.lxl.adapter.loginadapter.ResultMsg;

public class LoginForSinaAdapter {
    public boolean support(Object adapter) {
        return adapter instanceof LoginForSinaAdapter;
    }
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
