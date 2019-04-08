package com.lxl.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ApiOperatorDemo implements Watcher {

	// private final static String CONNECTSTRING =
	// "192.168.122.139:2181,192.168.122.140:2181,192.168.122.141:2181";

	private final static String CONNECTSTRING = "192.168.122.139:2181";
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static ZooKeeper zookeeper;
	private static Stat stat = new Stat();
	private static Stat subStat = new Stat();

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		zookeeper = new ZooKeeper(CONNECTSTRING, 5000, new ApiOperatorDemo());
		countDownLatch.await();

		// // 创建节点
		// String nodePath = "/node1";
		// stat = zookeeper.exists(nodePath, true);
		// if (stat == null) {// 表示节点不存在
		// // ZooDefs.Ids.OPEN_ACL_UNSAFE 5中权限 CreateMode.PERSISTENT 节点类型
		// String result = zookeeper.create(nodePath, "123".getBytes(),
		// ZooDefs.Ids.OPEN_ACL_UNSAFE,
		// CreateMode.PERSISTENT);
		// TimeUnit.SECONDS.sleep(1);
		// // zookeeper.getData("/node1",new ZkClientApiOperatorDemo(),stat);
		// // //增加一个
		// System.out.println("创建成功：" + result);
		// }
		//
		// // 修改数据
		// zookeeper.setData(nodePath, "mic123".getBytes(), -1);
		// Thread.sleep(2000);
		//
		// // 删除节点
		// zookeeper.delete(nodePath, -1);
		// Thread.sleep(2000);

		// // 创建节点和子节点
		// String path = "/test";
		// String subpath = "/node11";
		//
		// zookeeper.create(path + subpath, "123".getBytes(),
		// ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// TimeUnit.SECONDS.sleep(1);
		//
		// subStat = zookeeper.exists(path + subpath, true);
		// if (subStat == null) {// 表示节点不存在
		// zookeeper.create(path + subpath, "123".getBytes(),
		// ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// TimeUnit.SECONDS.sleep(1);
		// }
		// zookeeper.create(path + subpath +"111", "123".getBytes(),
		// ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// TimeUnit.SECONDS.sleep(1);
		// // 修改子路径
		// zookeeper.setData(path + subpath, "mic123".getBytes(), -1);
		// TimeUnit.SECONDS.sleep(1);
		//
		// // 获取指定节点下的子节点
		// List<String> childrens = zookeeper.getChildren(path, true);
		// System.out.println(childrens);

	}

	public void process(WatchedEvent watchedEvent) {
		// 如果当前的连接状态是连接成功的，那么通过计数器去控制
		if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
			if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
				countDownLatch.countDown();
				System.out.println(watchedEvent.getState() + "-->" + watchedEvent.getType());
			} else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
				try {
					System.out.println("数据变更触发路径：" + watchedEvent.getPath() + "->改变后的值："
							+ zookeeper.getData(watchedEvent.getPath(), true, stat));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {// 子节点的数据变化会触发
				try {
					System.out.println("子节点数据变更路径：" + watchedEvent.getPath() + "->节点的值："
							+ zookeeper.getData(watchedEvent.getPath(), true, subStat));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (watchedEvent.getType() == Event.EventType.NodeCreated) {// 创建子节点的时候会触发
				try {
					System.out.println("节点创建路径：" + watchedEvent.getPath() + "->节点的值："
							+ zookeeper.getData(watchedEvent.getPath(), true, stat));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {// 子节点删除会触发
				System.out.println("节点删除路径：" + watchedEvent.getPath());
			}
			System.out.println(watchedEvent.getType());
		}

	}
}
