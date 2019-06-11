package com.lxl.thread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition实例
 * @author Administrator
 *
 */
public class ConditionDemoWait implements Runnable {
	private Lock lock;
	private Condition condition;

	public ConditionDemoWait(Lock lock, Condition condition) {
		this.lock = lock;
		this.condition = condition;
	}

	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println("begin -ConditionDemoWait");
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("end - ConditionDemoWait");
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		new Thread(new ConditionDemoWait(lock, condition)).start();
		new Thread(new ConditionDemoSignal(lock, condition)).start();
		Thread.sleep(3000);
	}
}