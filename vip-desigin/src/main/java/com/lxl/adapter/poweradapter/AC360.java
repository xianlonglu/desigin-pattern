package com.lxl.adapter.poweradapter;

public class AC360 implements ACStrategy{

    public int outputAC(){
        int output = 360;
        System.out.println("输出电流" + output + "V");
        return output;
    }
}
