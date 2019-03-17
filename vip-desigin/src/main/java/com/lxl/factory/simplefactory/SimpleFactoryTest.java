package com.lxl.factory.simplefactory;

import com.lxl.factory.ICar;
import com.lxl.factory.QQCar;

public class SimpleFactoryTest {

    public static void main(String[] args) {

        CarFactory factory = new CarFactory();
        ICar car1 = factory.create1("QQ");
        car1.getCarName();
        
        ICar car2 = factory.create2("com.lxl.factory.KIACar");
        car2.getCarName();
        
        ICar car3 = factory.create3(QQCar.class);
        car3.getCarName();

    }
}
