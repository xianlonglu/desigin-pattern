package com.gupaoedu.vip.pattern.facade.general;

/**
 * 门面模式
 * @author Administrator
 *
 */
class Test {
    // 客户
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.doA();
        facade.doB();
        facade.doC();
    }
}