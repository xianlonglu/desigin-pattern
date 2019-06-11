package com.lxl.thread.lock.twinsLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TwinsLockTest {
	static CountDownLatch countDownLatch = new CountDownLatch(10);

	public static void main(String[] args) {
		final Lock lock = new TwinsLock();
		class WorkerTryLock extends Thread {
			public void run() {
				boolean locked = false;
				System.err.println(Thread.currentThread().getName() + "试图占有对象：tryLock()");
				locked = lock.tryLock();
				try {
					if (locked) {
						try {
							TimeUnit.SECONDS.sleep(1);
							System.out.println(Thread.currentThread().getName());
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} finally {
					countDownLatch.countDown();
					if (locked) {
						lock.unlock();
					}
				}
			}
		}
		class Worker extends Thread {
			public void run() {
				// while (true) {
				lock.lock();
				try {
					try {
						TimeUnit.SECONDS.sleep(1);
						System.out.println(Thread.currentThread().getName());
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} finally {
					countDownLatch.countDown();
					lock.unlock();
				}
				// }
			}
		}
		// 启动10个线程
		for (int i = 0; i < 10; i++) {
			// WorkerTryLock w = new WorkerTryLock();
			Worker w = new Worker();
			w.setDaemon(true);
			w.start();
		}

		// 每隔1秒换行
		for (int i = 0; i < 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}