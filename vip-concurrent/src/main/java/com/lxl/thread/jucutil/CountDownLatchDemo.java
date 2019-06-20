package com.lxl.thread.jucutil;

import java.util.concurrent.CountDownLatch;

/**
 * 等待多线程完成的CountDownLatch:允许一个或多个线程等待其他线程完成操作。
 * 
 * CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完 成，这里就传入N。
 * 
 * 当我们调用CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await方法会阻塞当前线程，直到N变成零。
 * 由于countDown方法可以用在任何地方，所以这里说的N个点，可以是N个线程，也可以是1个线程里的N个执行步骤。用在多个线程时，只需要把这个
 * CountDownLatch的引用传递到线程里即可。
 * 
 * 如果有某个解析sheet的线程处理得比较慢，我们不可能让主线程一直等待，所以可以使 用另外一个带指定时间的await方法——await（long
 * time，TimeUnit unit），这个方法等待特定时 间后，就会不再阻塞当前线程。
 * 
 * 注意 计数器必须大于等于0，只是等于0时候，计数器就是零，调用await方法时不会阻塞当前线程。
 * CountDownLatch不可能重新初始化或者修改CountDownLatch对象的内部计数器的值
 * 。一个线程调用countDown方法happen-before，另外一个线程调用await方法。
 * 
 * @author Administrator
 *
 */
public class CountDownLatchDemo extends Thread {

	static CountDownLatch countDownLatch1 = new CountDownLatch(1);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			new CountDownLatchDemo().start();
		}
		Thread.sleep(1000);
		countDownLatch1.countDown();

	}

	@Override
	public void run() {
		System.out.println("ThreadName- start:" + Thread.currentThread().getName());
		try {
			countDownLatch1.await(); // 阻塞 3个线程 Thread.currentThread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// TODO
		System.out.println("ThreadName- end:" + Thread.currentThread().getName());
	}


	public static void main2(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		new Thread(() -> {
			System.out.println("Thread1");
			countDownLatch.countDown(); // 3-1=2
		}).start();
		new Thread(() -> {
			System.out.println("Thread2");
			countDownLatch.countDown();// 2-1=1
		}).start();
		new Thread(() -> {
			System.out.println("Thread3");
			countDownLatch.countDown();// 1-1=0
		}).start();
		countDownLatch.await();
		System.out.println("Thread33");
	}

	static CountDownLatch c = new CountDownLatch(2);

	public static void main3(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
				c.countDown();
				System.out.println(2);
				c.countDown();
			}
		}).start();
		c.await();
		System.out.println("3");
	}
}
