package com.lxl.thread.work;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 组队队列
 * 
 * @author Administrator
 *
 */
public class work7 {
	static ArrayBlockingQueue<String> ab = new ArrayBlockingQueue(10);// FIFO的队列

	public static void main(String[] args) throws InterruptedException {
		// 线程池
		final ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				System.err.println("sendData:" + i);
				ab.add("data:" + i);
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		for (int i = 0; i < 4; i++) {
			new Thread(() -> {
				while (true) {
					try {
						// 阻塞方式获得元素
						String data = ab.take();
						System.out.println(Thread.currentThread().getName() + ",receive:" + data);
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "Thread" + i).start();
		}
	}

}
