package com.lxl.curator;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Curator操作
 * 
 * @author Administrator
 *
 */
public class CuratorOperatorDemo {

	private final static String PATH = "/event";
	
	public static void main(String[] args) throws Exception {
		CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
		System.out.println("连接成功.........");

		// createData(curatorFramework);
		// getData(curatorFramework);
		// deleteData(curatorFramework);
		// updateData(curatorFramework);
		// deleteData(curatorFramework);
		// asynData(curatorFramework);// 异步操作
		// transactionData(curatorFramework);// 事务操作
		TimeUnit.SECONDS.sleep(1);
		System.out.println("11");
		byte[] bytes = curatorFramework.getData().forPath(PATH);
		System.out.println("12");
		curatorFramework.setData().forPath(PATH, "up".getBytes());
		System.out.println("13");

		curatorFramework.create().withMode(CreateMode.PERSISTENT)
				.forPath(PATH + "/event1", "1".getBytes());
		TimeUnit.SECONDS.sleep(1);
		System.out.println("2");
		curatorFramework.setData().forPath(PATH + "/event1", "99".getBytes());
		TimeUnit.SECONDS.sleep(1);
		System.out.println("3");
		curatorFramework.delete().forPath(PATH + "/event1");
		System.out.println("4");

		curatorFramework.delete().forPath(PATH);
		System.out.println("14");
	}

	// PERSISTENT 持久化节点:创建节点后，不删除就永久存在
	// EPHEMERAL 临时节点:创建后，回话结束节点会自动删除
	// PERSISTENT_SEQUENTIAL 持久化有序节点:节点path末尾会追加一个10位数的单调递增的序列
	// EPHEMERAL_SEQUENTIAL 临时有序节点
	private static void createData(CuratorFramework curatorFramework) throws Exception {
		String result = curatorFramework.create().creatingParentsIfNeeded()
				.withMode(CreateMode.PERSISTENT).forPath(PATH, "test12".getBytes());
		System.out.println("createData, path = " + result);
	}

	private static void getData(CuratorFramework curatorFramework) throws Exception {
		Stat stat = new Stat();
		byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath(PATH);
		System.out.println("selectData, value=" + new String(bytes) + "-->stat:" + stat);
		
		List<String> list = curatorFramework.getChildren().forPath(PATH);
		for(String s : list) {
			System.out.println("list:"+s);
		}
		//判断是否存在，stat==null即不存在
		Stat stat1 = curatorFramework.checkExists().forPath(PATH);
		System.out.println("stat1:"+ stat1);
	}

	private static void updateData(CuratorFramework curatorFramework) throws Exception {
		// .withVersion(0)  // 指定数据版本
		Stat stat1 = curatorFramework.setData().forPath(PATH, "up".getBytes());
		System.out.println("updateData, stat=" + stat1);
	}

	private static void deleteData(CuratorFramework curatorFramework) throws Exception {
		// .withVersion(0) // 指定数据版本
		Stat stat = new Stat();
		String value = new String(curatorFramework.getData().storingStatIn(stat).forPath(PATH));
		// curatorFramework.delete().withVersion(stat.getVersion()).forPath(PATH);
		System.err.println("deleteData, value=" + value);
		// 默认情况下，version为-1
		curatorFramework.delete().deletingChildrenIfNeeded().forPath(PATH);
	}

	/**
	 * 异步操作
	 */
	private static void asynData(CuratorFramework curatorFramework) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(1);
		CountDownLatch countDownLatch = new CountDownLatch(1);
		try {
			curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
					.inBackground(new BackgroundCallback() {
						@Override
						public void processResult(CuratorFramework curatorFramework,
								CuratorEvent curatorEvent) throws Exception {
							System.out.println(Thread.currentThread().getName() + "->resultCode:"
									+ curatorEvent.getResultCode() + "->" + curatorEvent.getType());
							countDownLatch.countDown();
						}
					}, service).forPath("/mic", "123".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		countDownLatch.await();
		service.shutdown();

	}

	/**
	 * 事务操作（curator独有的）
	 */
	private static void transactionData(CuratorFramework curatorFramework) throws Exception {
		try {
			Collection<CuratorTransactionResult> resultCollections = curatorFramework
					.inTransaction().create().forPath("/mic1", "1121".getBytes()).and().setData()
					.forPath("/test11", "1211".getBytes()).and().delete().forPath("/mic").and()
					.commit();
			for (CuratorTransactionResult result : resultCollections) {
				System.out.println(result.getForPath() + "->" + result.getType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
