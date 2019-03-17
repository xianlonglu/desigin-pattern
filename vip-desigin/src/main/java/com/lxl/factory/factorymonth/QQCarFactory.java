package com.lxl.factory.factorymonth;

import com.lxl.factory.ICar;
import com.lxl.factory.QQCar;

public class QQCarFactory implements ICarFactory {

	public ICar create() {
		return new QQCar();
	}

}
