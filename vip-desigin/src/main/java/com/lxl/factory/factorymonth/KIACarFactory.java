package com.lxl.factory.factorymonth;

import com.lxl.factory.ICar;
import com.lxl.factory.KIACar;

public class KIACarFactory implements ICarFactory {

	public ICar create() {
		return new KIACar();
	}

}
