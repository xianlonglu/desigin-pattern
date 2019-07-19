package com.lxl.curator.selector;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class LeaderSelectorClientC extends LeaderSelectorListenerAdapter implements Closeable {

	private String name; // 表示当前的进程
	private LeaderSelector leaderSelector; // leader选举的API
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public LeaderSelectorClientC(CuratorFramework client, String path, String name) {
		this.name = name;
		this.leaderSelector = new LeaderSelector(client, path, this);
		// 在大多数情况下，我们会希望一个 selector 放弃 leader 后还要重新参与 leader 选举
		leaderSelector.autoRequeue(); // 自动抢
	}

	public void start() {
		leaderSelector.start(); // 开始竞争leader
	}

	@Override
	public void takeLeadership(CuratorFramework client) throws Exception {
		// 这个方法执行结束之后，表示释放leader权限
		System.out.println(name + " 现在是 leader 了，持续成为 leader ");
		countDownLatch.await(); // 阻塞当前的进程防止leader丢失
	}

	@Override
	public void close() throws IOException {
		leaderSelector.close();
	}

	private static String CONNECTION_STR = "127.0.0.1:2181";

	public static void main(String[] args) throws IOException {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
				.connectString(CONNECTION_STR).sessionTimeoutMs(10000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		curatorFramework.start();
		LeaderSelectorClientA lsc = new LeaderSelectorClientA(curatorFramework, "/leader", "ClientC");
		lsc.start(); // 开始选举
		System.in.read();
	}
}
