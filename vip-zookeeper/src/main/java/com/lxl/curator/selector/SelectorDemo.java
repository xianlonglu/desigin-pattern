package com.lxl.curator.selector;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import com.google.common.collect.Lists;

/**
 * 选举demo1，多个客户端自动抢
 * 
 * @author Administrator
 *
 */
public class SelectorDemo {
	private final static String CONNECTSTRING = "127.0.0.1:2181";

	private static final String MASTER_PATH = "/curator_master_path1";

	private static final int CLIENT_QTY = 5; // 客户端数量

	public static void main(String[] args) throws Exception {
		System.out.println("创建" + CLIENT_QTY + "个客户端，");
		List<CuratorFramework> clients = Lists.newArrayList();
		List<ExampleClient> examples = Lists.newArrayList();
		try {
			for (int i = 0; i < CLIENT_QTY; i++) {
				CuratorFramework client = CuratorFrameworkFactory.builder()
						.connectString(CONNECTSTRING).sessionTimeoutMs(5000)
						.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
				clients.add(client);
				client.start();

				ExampleClient exampleClient = new ExampleClient(client, MASTER_PATH, "Client:" + i);
				examples.add(exampleClient);
				exampleClient.start();
			}
			System.in.read();
		} finally {
			for (ExampleClient exampleClient : examples) {
				CloseableUtils.closeQuietly(exampleClient);
			}
			for (CuratorFramework client : clients) {
				CloseableUtils.closeQuietly(client);
			}
		}
	}
}
