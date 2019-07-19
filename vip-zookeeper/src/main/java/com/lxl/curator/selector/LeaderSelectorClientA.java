package com.lxl.curator.selector;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Curator 有两种选举 recipe（Leader Latch 和 Leader Election）
 * 
 * Leader Latch 参与选举的所有节点，会创建一个顺序节点，其中最小的 节点会设置为 master 节点, 没抢到 Leader 的节点都监听
 * 前一个节点的删除事件，在前一个节点删除后进行重新抢 主，当 master 节点手动调用 close（）方法或者 master
 * 节点挂了之后，后续的子节点会抢占 master。 其中 spark 使用的就是这种方法。
 * 
 * LeaderSelector 和 Leader Latch 最的差别在于，leader 可以释放领导权以后，还可以继续参与竞争
 * 
 * @author Administrator
 *
 */
public class LeaderSelectorClientA extends LeaderSelectorListenerAdapter implements Closeable {

	private String name; // 表示当前的进程
	private LeaderSelector leaderSelector; // leader选举的API
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public LeaderSelectorClientA(CuratorFramework client, String path, String name) {
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
		LeaderSelectorClientA lsc = new LeaderSelectorClientA(curatorFramework, "/leader", "ClientA");
		lsc.start(); // 开始选举
		System.in.read();
	}
}
