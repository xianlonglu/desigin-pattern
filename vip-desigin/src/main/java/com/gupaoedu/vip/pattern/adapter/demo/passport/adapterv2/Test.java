package com.gupaoedu.vip.pattern.adapter.demo.passport.adapterv2;

/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        IPassportForThird adapter = new PassportForThirdAdapter();
        System.err.println(adapter.loginForQQ("sdfasdfasfasfas").toString());
    }
}
