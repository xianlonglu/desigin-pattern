package com.lxl.adapter.loginadapter.v2.adapters;

import com.lxl.adapter.loginadapter.ResultMsg;

public interface RegistAdapter {
    boolean support(Object adapter);
    ResultMsg login(String id, Object adapter);
}
