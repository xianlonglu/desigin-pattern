package com.lxl.thread.blockingqueue;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 组队队列
 * 
 * @author Administrator
 *
 */
public class BlockingDemo {
	ArrayBlockingQueue<String> ab = new ArrayBlockingQueue(10);// FIFO的队列
	{
		init(); // 构造块初始化
	}

	public void init() {
		Iterator<String> it = ab.iterator();

		new Thread(() -> {
			while (true) {
				// for (;;) {
				try {
					// 阻塞方式获得元素
					String data = ab.take();
					System.out.println("receive:" + data);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void addData(String data) throws InterruptedException {
		System.err.println("sendData:" + data);
		ab.add(data);
		Thread.sleep(1000);
	}

	public static void main(String[] args) throws InterruptedException {
		BlockingDemo blockingDemo = new BlockingDemo();

		for (int i = 0; i < 1000; i++) {
			blockingDemo.addData("data:" + i);
		}

	}

}
