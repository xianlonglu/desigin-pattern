package com.gupaoedu.vip;

/**
 * 接口
 */
public interface IHelloService {

    //
    String sayHello(String content);

    /**
     * 保存角色
     * @param role
     * @return String
     */
    String saveRole(Role role);

}
