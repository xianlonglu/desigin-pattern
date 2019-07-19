package com.lxl.rpc.provider;

import com.lxl.rpc.api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {

    public String hello(String name) {  
        return "Hello " + name + "!";  
    }  
  
}  
