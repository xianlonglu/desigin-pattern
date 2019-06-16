package com.lxl.thread.work;

import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition实现阻塞队列： 下面通过一个有界队列的示例来 深入了解Condition的使用方式。
 * 有界队列是一种特殊的队列，当队列为空时，队列的获取操作 将会阻塞获取线程，直到队列中有新增元素。 当队列已满时，队列的插入操作将会阻塞插入线
 * 程，直到队列出现“空位”，
 * 
 * 通过put方法添加一个元素，通过take()方法移出一个 元素。 以添加方法为例，首先需要获得锁，目的是确保数组修改的可见性和排他性。
 * 当数组数量等于数组长度时， 表示数组已满，则调用notFull.await()，当前线程随之释放锁并进入等待状态。
 * 如果数组数量不等于数组长度，表示数组未满，则添加元素到数组中，同时通知等待在notEmpty上的线程，数 组中已经有新元素可以获取。
 * 在添加和删除方法中使用while循环而非if判断，目的是防止过早或意外的通知，只有条件 符合才能够退出循环。
 * 回想之前提到的等待/通知的经典范式，二者是非常类似的。
 * 
 * @author Administrator
 *
 */
public class work5 {

	private Object[] items;
	// 添加的下标，删除的下标和数组当前数量
	private int putIndex, takeIndex, count;
	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();

	public work5(int size) {
		items = new Object[size];
	}

	// 添加一个元素，如果数组满，则添加线程进入等待状态，直到有"空位"
	public void put(Object t) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length)
				notFull.await();
			System.err.println("put位置" + putIndex + ",内容=" + t);
			Thread.sleep(100);
			items[putIndex] = t;
			if (++putIndex == items.length)
				putIndex = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	// 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新添加元素
	@SuppressWarnings("unchecked")
	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			Object x = items[takeIndex];
			System.out.println("take位置" + takeIndex + ",内容=" + x);
			Thread.sleep(100);
			if (++takeIndex == items.length)
				takeIndex = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}

	public void initRemove() {
		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(100);
					take();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void main(String[] args) throws InterruptedException {
		work5 b = new work5(5);
		b.initRemove();
		for (int i = 0; i < 10; i++) {
			b.put(i);
			/*
			 * if (i == 4) { b.initRemove(); }
			 */
		}

	}
}
