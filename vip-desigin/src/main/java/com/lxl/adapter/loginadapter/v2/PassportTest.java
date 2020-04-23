package com.lxl.adapter.loginadapter.v2;

import com.lxl.adapter.loginadapter.ResultMsg;

public class PassportTest {

	public static void main(String[] args) {
		IPassportForThird passportForThird = new PassportForThirdAdapter();
		ResultMsg msg = passportForThird.loginForQQ("123");
		System.out.println(msg);

	}

}
