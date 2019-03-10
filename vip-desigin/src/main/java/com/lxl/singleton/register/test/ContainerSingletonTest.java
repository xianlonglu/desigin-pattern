package com.lxl.singleton.register.test;

import com.lxl.singleton.ConcurrentExecutor;
import com.lxl.singleton.register.ContainerSingleton;


public class ContainerSingletonTest {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            ConcurrentExecutor.execute(new ConcurrentExecutor.RunHandler() {
                public void handler() {
                    Object obj = ContainerSingleton.getInstance("com.lxl.singleton.register.test.Pojo");
                    System.out.println(System.currentTimeMillis() + ": " + obj);
                }
            }, 10,6);
            long end = System.currentTimeMillis();
            System.out.println("总耗时：" + (end - start) + " ms.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
