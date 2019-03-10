package com.lxl.factory.factorymonth;

public class FactoryMethodTest {

	public static void main(String[] args) {
		ICarFactory kiaCarFactory = new KIACarFactory();
		kiaCarFactory.create().getCarName();
		

		ICarFactory qqCarFactory = new QQCarFactory();
		qqCarFactory.create().getCarName();
	}

}
