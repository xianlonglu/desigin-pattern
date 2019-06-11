package com.lxl.thread.jucutil;

import java.util.concurrent.CyclicBarrier;

/**
 * 
 * CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数
 * 量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞，直到最后一个线程到达屏障时，屏障才会
 * 开门，所有被屏障拦截的线程才会继续运行。
 * 
 * @author Administrator
 *
 */
public class CyclicBarrierTest {
	/**
	 * 如果把new CyclicBarrier(2)修改成new CyclicBarrier(3)，则主线程和子线程会永远等待，
	 * 因为没有第三个线程执行await方法，即没有第三个线程到达屏障，所以之前到达屏障的两个 线程都不会继续执行。
	 */
	static CyclicBarrier c = new CyclicBarrier(2);

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (Exception e) {
				}
				System.out.println(1);
			}
		}).start();
		/*
		 * 因为主线程和子线程的调度是由CPU决定的，两个线程都有可能先执行，所以会产生两种 输出，第一种可能输出如下。
		 */
		try {
			c.await();
		} catch (Exception e) {
		}
		System.out.println(2);
	}
}