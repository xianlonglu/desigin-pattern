package com.lxl.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁 ：重进入是指任意线程在获取到锁之后能够再次获取该锁而不会被锁所阻塞，该特性的实 现需要解决以下两个问题。
 * 1）线程再次获取锁。锁需要去识别获取锁的线程是否为当前占据锁的线程，如果是，则再 次成功获取。
 * 2）锁的最终释放。线程重复n次获取了锁，随后在第n次释放该锁后，其他线程能够获取到该锁。
 * 锁的最终释放要求锁对于获取进行计数自增，计数表示当前锁被重复获取的次数，而锁 被释放时，计数自减，当计数等于0时表示锁已经成功释放。
 * 
 * @author Administrator
 *
 */
public class ReentrantLockDemo {

	private static int count = 0;
	static Lock lock = new ReentrantLock();

	public static void inc() {
		lock.lock();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
		lock.unlock();
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			// new Thread(AtomicDemo::inc).start();
			// new Thread(() -> Demo.inc()).start();
			new Thread(() -> {
				ReentrantLockDemo.inc();
			}).start();
		}
		Thread.sleep(3000);
		System.out.println("result:" + count);
	}
}
