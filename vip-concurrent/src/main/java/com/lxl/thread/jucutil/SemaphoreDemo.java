package com.lxl.thread.jucutil;

import java.util.concurrent.Semaphore;

/**
 * 控制并发线程数的Semaphore
 * 
 * Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以 保证合理的使用公共资源。
 * 信号灯,场景:比较常见的就是用来做限流操作了
 * 
 * Semaphore还提供一些其他方法，具体如下。 
 * 1、intavailablePermits()：返回此信号量中当前可用的许可证数。
 * 2、intgetQueueLength()：返回正在等待获取许可证的线程数。
 * 3、booleanhasQueuedThreads()：是否有线程正在等待获取许可证。 
 * 4、void reducePermits（int reduction）：减少reduction个许可证，是个protected方法。 
 * 5、Collection getQueuedThreads()：返回所有等待获取许可证的线程集合，是个protected方法。
 * 
 * @author Administrator
 *
 */
public class SemaphoreDemo {

	// 限流（AQS）
	// permits; 令牌(5)
	// 公平和非公平
	static class Car extends Thread {
		private int num;
		private Semaphore semaphore;

		public Car(int num, Semaphore semaphore) {
			this.num = num;
			this.semaphore = semaphore;
		}

		public void run() {
			try {
				semaphore.acquire(); // 获得一个令牌, 如果拿不到令牌，就会阻塞
				System.out.println("第" + num + " 抢占一个车位");
				Thread.sleep(2000);
				System.out.println("第" + num + " 开走喽");
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(5);
		for (int i = 0; i < 10; i++) {
			new Car(i, semaphore).start();
		}
	}

}
