package com.lxl.thread;

/**
 * Join实例
 * 
 * join用于让当前执行线程等待join线程执行结束。其实现原理是不停检查join线程是否存 活，如果join线程存活则让当前线程永远等待。
 * 
 * @author Administrator
 *
 */
public class JoinExample {
	static int x = 0;

	public static void main(String[] args) throws InterruptedException {
		Thread t11 = new Thread(() -> {
			x = 100;
		});
		t11.start();
		t11.join();
		System.out.println(x);

		Thread t = new Thread(new Runnable() {
			public void run() {
				System.out.println("First task started");
				System.out.println("Sleeping for 2 seconds");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("First task completed");
			}
		});
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("Second task completed");
			}
		});
		t.start(); // Line 15
		t.join(); // Line 16
		t1.start();
	}
}
