package com.lxl.factory.abstractfactory;


public class QQCarFactory implements CarFactory {

	public IName createName() {
		return new KIAName();
	}

	public IPrice createPrice() {
		return new KIAPrice();
	}

}
