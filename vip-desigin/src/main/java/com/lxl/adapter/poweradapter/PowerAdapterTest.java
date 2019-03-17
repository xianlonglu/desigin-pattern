package com.lxl.adapter.poweradapter;

public class PowerAdapterTest {
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outoupDC5V();

    	//适配器
        DC5 dc51 = new PowerAdapter(PowerAdapter.getACStrategy("ac360"));
        dc51.outoupDC5V();
    }
}
