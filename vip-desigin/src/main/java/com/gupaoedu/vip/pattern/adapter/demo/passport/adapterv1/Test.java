package com.gupaoedu.vip.pattern.adapter.demo.passport.adapterv1;

/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        PassportForThirdAdapter adapter = new PassportForThirdAdapter();
        System.err.println(adapter.login("tom","123456").toString());
        System.err.println(adapter.loginForQQ("sjooguwoersdfjhasjfsa").toString());
        System.err.println(adapter.loginForWechat("slfsjoljsdo8234ssdfs").toString());
    }
}
