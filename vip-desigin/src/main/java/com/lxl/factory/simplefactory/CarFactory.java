package com.lxl.factory.simplefactory;

import com.lxl.factory.ICar;
import com.lxl.factory.KIACar;
import com.lxl.factory.QQCar;

public class CarFactory {
	public ICar create1(String name) {
		if ("QQ".equals(name)) {
			return new QQCar();
		} else if ("KIA".equals(name)) {
			return new KIACar();
		} else {
			return null;
		}
	}

	public ICar create2(String className) {
		try {
			if (null != className && !"".equals(className)) {
				return (ICar) Class.forName(className).newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ICar create3(Class<? extends ICar> clazz) {
		try {
			if (clazz != null) {
				return clazz.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
