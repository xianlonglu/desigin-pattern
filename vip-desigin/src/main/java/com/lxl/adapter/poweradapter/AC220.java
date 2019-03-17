package com.lxl.adapter.poweradapter;

public class AC220 implements ACStrategy{

    public int outputAC(){
        int output = 220;
        System.out.println("输出电流" + output + "V");
        return output;
    }
}
