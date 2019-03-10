package com.lxl.factory.abstractfactory;

public interface CarFactory {
	IName createName();

	IPrice createPrice();
}
