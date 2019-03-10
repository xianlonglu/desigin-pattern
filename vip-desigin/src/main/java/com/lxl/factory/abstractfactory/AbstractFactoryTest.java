package com.lxl.factory.abstractfactory;

public class AbstractFactoryTest {

	public static void main(String[] args) {
		CarFactory qqCarFactory = new QQCarFactory();
		qqCarFactory.createName().getName();
		qqCarFactory.createPrice().getPrice();

		CarFactory kiaCarFactory = new KIACarFactory();
		kiaCarFactory.createName().getName();
		kiaCarFactory.createPrice().getPrice();
	}

}
