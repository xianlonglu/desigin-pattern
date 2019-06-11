package com.lxl.thread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Condition实例
 * @author Administrator
 *
 */
public class ConditionDemoSignal implements Runnable {
	private Lock lock;
	private Condition condition;

	public ConditionDemoSignal(Lock lock, Condition condition) {
		this.lock = lock;
		this.condition = condition;
	}

	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println("begin -ConditionDemoNotify");
			condition.signal(); // 阻塞
			System.out.println("end - ConditionDemoNotify");
		} finally {
			lock.unlock();
		}
	}
}