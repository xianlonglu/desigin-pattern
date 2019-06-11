package com.lxl.thread.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 验证公平锁与非公平锁（代码不全可能不准）
 * 
 * Java并发编程的艺术.pdf中 5.3重入锁最后边
 * 
 * @author Administrator
 *
 */
public class FairAndUnfairTest {

	public static void main(String[] args) throws InterruptedException {
		FairAndUnfairTest f = new FairAndUnfairTest();
		f.testLock("非公平锁", fairLock);
		TimeUnit.SECONDS.sleep(2);
		f.testLock("公平锁", unfairLock);
	}

	private static Lock fairLock = new ReentrantLock2(true);
	private static Lock unfairLock = new ReentrantLock2(false);

	private void testLock(String type, Lock lock) throws InterruptedException {
		// 启动5个Job（略）
		// for (int i = 0; i < 5; i++) {
		// new Thread(new Job(lock), "" + i).start();
		// TimeUnit.MILLISECONDS.sleep(10);
		// }

		System.out.println(type);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Job(lock)) {
				public String toString() {
					return getName();
				}
			};
			thread.setName("" + i);
			thread.start();
		}
		Thread.sleep(11000);
	}

	private static class Job extends Thread {
		private Lock lock;

		public Job(Lock lock) {
			this.lock = lock;
		}

		public void run() {// 连续2次打印当前的Thread和等待队列中的Thread（略）
			// try {
			// lock.lock();
			// print(lock, false);
			// } catch (Exception e) {
			// } finally {
			// lock.unlock();
			// }

			for (int i = 0; i < 2; i++) {
				lock.lock();
				try {
					Thread.sleep(1000);
					System.out.println("获取锁的当前线程"+i+"[" + Thread.currentThread().getName() + "], 同步队列中的线程"
							+ ((ReentrantLock2) lock).getQueuedThreads() + "");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

	private static void print(Lock lock, boolean flag) throws InterruptedException {
		String Threadname = "lock by " + Thread.currentThread().getName() + ",Waiting by ";

		ReentrantLock2 r = (ReentrantLock2) lock;
		Collection<Thread> c = r.getQueuedThreads();
		List<String> list = new ArrayList();
		c.forEach(e -> {
			list.add(e.getName());
		});
		Threadname += list.toString();
		if (flag) {
			System.out.println(Threadname);
		} else {
			System.err.println(Threadname);
			TimeUnit.MILLISECONDS.sleep(100);
		}
	}

	private static class ReentrantLock2 extends ReentrantLock {
		public ReentrantLock2(boolean fair) {
			super(fair);
		}

		public Collection<Thread> getQueuedThreads() {
			List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
			Collections.reverse(arrayList);
			return arrayList;
		}
	}
}
