package com.gupaoedu.vip.pattern.singleton.threadlocal;

/**
 * Created by Tom.
 */
// 最后赠送给大家一个彩蛋，讲讲线程单例实现 ThreadLocal。
// ThreadLocal 不能保证其创建的对象是全局唯一的，但是能保证在单个线程中是唯一的，天生是线程安全的。
// 在主线程中无论调用多少次，获取到的实例都是同一个，都在两个子线程中分别获取到了不同的实例
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance =
            new ThreadLocal<ThreadLocalSingleton>(){
                @Override
                protected ThreadLocalSingleton initialValue() {
                    return new ThreadLocalSingleton();
                }
            };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return threadLocalInstance.get();
    }
}
