package com.lxl.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 分布式锁
 * 
 * curator 对于锁这块做了一些封装，curator 提供了 InterProcessMutex 这样一个 api。
 * 
 * 除了分布式锁之外， 还提供了leader选举、分布式队列等常用的功能。
 * 
 * InterProcessMutex：分布式可重入排它锁
 * 
 * InterProcessSemaphoreMutex：分布式排它锁
 * 
 * InterProcessReadWriteLock：分布式读写锁
 * 
 * @author Administrator
 *
 */
public class CuratorLockDemo {

	private static String CONNECTION_STR = "127.0.0.1:2181";

	public static void main(String[] args) throws Exception {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
				.connectString(CONNECTION_STR).sessionTimeoutMs(50000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		curatorFramework.start();

		final InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/locks");

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "->尝试竞争锁");
				try {
					lock.acquire(); // 阻塞竞争锁

					System.out.println(Thread.currentThread().getName() + "->成功获得了锁");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					try {
						lock.release(); // 释放锁
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, "Thread-" + i).start();
		}

	}

}
