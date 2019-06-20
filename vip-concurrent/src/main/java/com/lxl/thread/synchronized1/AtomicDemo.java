package com.lxl.thread.synchronized1;

/**
 * 原子操作
 * 
 * @author Administrator
 *
 */
public class AtomicDemo {
	private static int count = 0;

	public static void inc() {
		synchronized (AtomicDemo.class) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
	}

	public static void inc1() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			// new Thread(AtomicDemo::inc).start();
			new Thread(() -> AtomicDemo.inc1()).start();
		}
		Thread.sleep(2000);
		System.out.println("运行结果" + count);
	}
}
