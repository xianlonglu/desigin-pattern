package com.gupaoedu.vip.pattern.facade.points;

import com.gupaoedu.vip.pattern.facade.general.Facade;

/**
 * Created by Tom.
 */
public class Test {

    public static void main(String[] args) {

        FacadeService facadeService = new FacadeService();

        GiftInfo giftInfo = new GiftInfo("《Spring 5核心原理》");

        facadeService.exchange(giftInfo);

    }

}
