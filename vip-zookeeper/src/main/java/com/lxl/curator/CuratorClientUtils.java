package com.lxl.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

//创建连接
public class CuratorClientUtils {

	private static CuratorFramework curatorFramework;
	private final static String CONNECTSTRING = "127.0.0.1:2181";
	private final static String CONNECTION_STR = "192.168.13.102:2181,192.168.13.103:2181,192.168.13.104:2181";

	// 创建会话的两种方式 normal
	public static CuratorFramework getInstance() {
		curatorFramework = CuratorFrameworkFactory.newClient(CONNECTSTRING, 5000, 5000,
				new ExponentialBackoffRetry(1000, 3));
		curatorFramework.start();
		return curatorFramework;
	}

	// curator连接的重试策略
	// 1.RetryNTimes(int n, int sleepMsBetweenRetries) // 指定最大重试次数的重试策略
	// 2.RetryOneTime(int sleepMsBetweenRetry) // 仅重试一次
	// 3.RetryUntilElapsed(int maxElapsedTimeMs, int sleepMsBetweenRetries)
	// 以sleepMsBetweenRetries的间隔重连,直到超过maxElapsedTimeMs的时间设置
	// 4.ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries)
	// ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries, int  maxSleepMs)
	// 每一次重试之间停顿的时间逐渐增加；重试指定的次数；maxSleepMs：超过此时间，则不再重试。
	// 5.RetryForever(retryIntervalMs) // 永远重试，不推荐使用
	// fluent风格
	public static CuratorFramework getInstance1() {
		curatorFramework = CuratorFrameworkFactory.builder().connectString(CONNECTSTRING)
				.sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.namespace("test").build();
		curatorFramework.start();// 启动Curator客户端
		return curatorFramework;
	}

	/**
	 * 关闭zk客户端连接
	 */
	private void closeZKClient() {
		if (curatorFramework != null) {
			this.curatorFramework.close();
		}
	}
}
