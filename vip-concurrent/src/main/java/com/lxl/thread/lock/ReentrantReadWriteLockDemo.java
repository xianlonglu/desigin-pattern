package com.lxl.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 
 * Cache组合一个非线程安全的HashMap作为缓存的实现，同时使用读写锁的 读锁和写锁来保证Cache是线程安全的。 在读操作get(String
 * key)方法中，需要获取读锁，这使 得并发访问该方法时不会被阻塞。写操作put(String key,Object
 * value)方法和clear()方法，在更新 HashMap时必须提前获取写锁，当获取写锁后，其他线程对于读锁和写锁的获取均被阻塞，而
 * 只有写锁被释放之后，其他读写操作才能继续。Cache使用读写锁提升读操作的并发性，也保 证每次写操作对所有的读写操作的可见性，同时简化了编程方式。
 * 
 * @author Administrator
 *
 */
public class ReentrantReadWriteLockDemo {
	static Map<String, Object> cacheMap = new HashMap<>();
	static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	static Lock read = rwl.readLock();
	static Lock write = rwl.writeLock();
	static {
		cacheMap.put("key", 1);
	}

	public static final Object get(String key) {
		// System.out.println("开始读取数据");
		read.lock(); // 读锁
		try {
			return cacheMap.get(key);
		} finally {
			read.unlock();
		}
	}

	public static final Object put(String key, Object value) {
		write.lock();
		System.out.println("开始写数据");
		try {
			return cacheMap.put(key, value);
		} finally {
			write.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				new Thread(() -> System.err.println(ReentrantReadWriteLockDemo.put("key", 100))).start();
				Thread.sleep(2000);
			}
			new Thread(() -> System.err.println(ReentrantReadWriteLockDemo.get("key"))).start();
		}
		Thread.sleep(4000);
		System.err.println("结束");
	}
}
