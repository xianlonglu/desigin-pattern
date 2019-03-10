package com.lxl.factory.abstractfactory;


public class KIACarFactory implements CarFactory {

	public IName createName() {
		return new QQName();
	}

	public IPrice createPrice() {
		return new QQPrice();
	}

}
