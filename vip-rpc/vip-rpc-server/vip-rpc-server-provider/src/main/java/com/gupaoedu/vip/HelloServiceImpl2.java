package com.gupaoedu.vip;

/**
 * 实现类
 */
@RpcService(value = IHelloService.class,version = "v2.0")
public class HelloServiceImpl2 implements IHelloService{

    @Override
    public String sayHello(String content) {
        System.out.println("【v2.0】request in sayHello:"+content);
        return "【v2.0】Say Hello:"+content;
    }

    @Override
    public String saveRole(Role role) {
        System.out.println("【V2.0】request in saveRole:"+role);
        return "【V1.0】SUCCESS";
    }
}
