package com.lxl.thread.jucutil;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier还提供一个更高级的构造函数CyclicBarrier（int parties，Runnable barrierAction），
 * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景，
 * 
 * @author Administrator
 *
 */
public class CyclicBarrierTest2 {
	static CyclicBarrier c = new CyclicBarrier(2, new A());

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.err.println("执行任务1");
					c.await();
				} catch (Exception e) {
				}
				System.out.println(1);
			}
		}).start();
		try {
			System.err.println("执行任务2");
			c.await();
		} catch (Exception e) {
		}
		System.out.println(2);
	}

	static class A implements Runnable {
		@Override
		public void run() {
			System.out.println(3);
		}
	}
}
