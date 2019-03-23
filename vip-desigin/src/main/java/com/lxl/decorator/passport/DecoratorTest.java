package com.lxl.decorator.passport;

import com.lxl.decorator.passport.old.SigninService;
import com.lxl.decorator.passport.upgrade.ISiginForThirdService;
import com.lxl.decorator.passport.upgrade.SiginForThirdService;

public class DecoratorTest {

    public static void main(String[] args) {

        //满足一个is-a
        ISiginForThirdService siginForThirdService = new SiginForThirdService(new SigninService());
        siginForThirdService.loginForQQ("sdfasfdasfsf");

    }


}
