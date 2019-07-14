package com.lxl.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.CreateMode;

/**
 * Curator 事件监听
 * 
 * @author Administrator
 *
 */
public class CuratorEventDemo {

	private final static String PATH = "/event";

	/**
	 * 三种watcher来做节点的监听
	 * 
	 * pathcache 监视一个路径下子节点的创建、删除、节点数据更新
	 * 
	 * NodeCache 监视一个节点的创建、更新、删除
	 * 
	 * TreeCache pathcaceh+nodecache 的合体（监视路径下的创建、更新、删除事件）， 缓存路径下的所有子节点的数据
	 */
	public static void main(String[] args) throws Exception {
		CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
		// addListenerWithNode(curatorFramework);
		// addListenerWithChild(curatorFramework);
		// addListenerWithTreeCache(curatorFramework);
		// test(curatorFramework);

		System.in.read();
	}

	private static void test(CuratorFramework curatorFramework) throws Exception {
		// curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(PATH,
		// "event".getBytes());
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

	// 配置中心
	// 创建、修改、删除
	private static void addListenerWithNode(CuratorFramework curatorFramework) throws Exception {
		NodeCache nodeCache = new NodeCache(curatorFramework, PATH, false);
		NodeCacheListener nodeCacheListener = () -> {
			System.out.println("节点数据发生变化,路径" + nodeCache.getCurrentData().getPath() + ",变化后的结果"
					+ new String(nodeCache.getCurrentData().getData()));
		};
		nodeCache.getListenable().addListener(nodeCacheListener);
		nodeCache.start();
		// nodeCache.start(true);
	}

	// 实现服务注册中心的时候，可以针对服务做动态感知
	private static void addListenerWithChild(CuratorFramework curatorFramework) throws Exception {
		PathChildrenCache nodeCache = new PathChildrenCache(curatorFramework, PATH, true);
		PathChildrenCacheListener nodeCacheListener = (curatorFramework1, pathChildrenCacheEvent) -> {
			System.out.print(pathChildrenCacheEvent.getType() + "->"
					+ pathChildrenCacheEvent.getData().getPath() + ",value="
					+ new String(pathChildrenCacheEvent.getData().getData()));
			switch (pathChildrenCacheEvent.getType()) {
			case INITIALIZED:
				System.out.println(" 初始化");
				break;
			case CHILD_ADDED:
				System.out.println(" 增加子节点");
				break;
			case CHILD_REMOVED:
				System.out.println(" 删除子节点");
				break;
			case CHILD_UPDATED:
				System.out.println(" 更新子节点");
				break;
			default:
				System.out.println("Child default");
				break;
			}
		};
		nodeCache.getListenable().addListener(nodeCacheListener);
		nodeCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
		// NORMAL //异步初始化，在这种模式下，同样的无法获取子节点列表，并且也会触发添加子节点事件，但是不会触发子节点初始化完成事件。
		// BUILD_INITIAL_CACHE // 同步初始化模式
		// POST_INITIALIZED_EVENT // 异步初始化，初始化之后会触发事件:获取不到子节点列表的
		// 通常使用异步初始化的情况下，都是使用POST_INITIALIZED_EVENT模式，NORMAL较为少用
	}

	private static void addListenerWithTreeCache(CuratorFramework curatorFramework)
			throws Exception {
		TreeCache treeCache = new TreeCache(curatorFramework, PATH);
		TreeCacheListener treeCacheListener = (curatorFramework1, treeCacheEvent) -> {
			System.out.print(treeCacheEvent.getType() + "->" + treeCacheEvent.getData().getPath()
					+ ",value=" + new String(treeCacheEvent.getData().getData()));
			switch (treeCacheEvent.getType()) {
			case NODE_ADDED:
				System.out.println(" 增加子节点");
				break;
			case NODE_REMOVED:
				System.out.println(" 删除子节点");
				break;
			case NODE_UPDATED:
				System.out.println(" 更新子节点");
				break;
			default:
				System.out.println("Tree default");
				break;
			}
		};
		treeCache.getListenable().addListener(treeCacheListener);
		treeCache.start();
	}
}
