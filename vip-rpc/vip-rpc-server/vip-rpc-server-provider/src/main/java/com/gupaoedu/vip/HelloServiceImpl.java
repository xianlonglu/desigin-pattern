package com.gupaoedu.vip;

/**
 * 实现类
 */
@RpcService(value = IHelloService.class, version = "v1.0")
public class HelloServiceImpl implements IHelloService{

    @Override
    public String sayHello(String content) {
        System.out.println("【V1.0】request in sayHello:"+content);
        return "【V1.0】Say Hello:"+content;
    }

    @Override
    public String saveRole(Role role) {
        System.out.println("【V1.0】request in saveRole:"+role);
        return "【V1.0】SUCCESS";
    }
}
