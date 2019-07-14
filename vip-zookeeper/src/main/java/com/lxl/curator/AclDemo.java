package com.lxl.curator;

import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

/**
 * 节点访问权限
 * 
 * @author Administrator
 *
 */
public class AclDemo {

	// 1、world：默认方式，相当于全世界都能访问
	// 2、auth：代表已经认证通过的用户(cli中可以通过addauth digest user:pwd 来添加当前上下文中的授权用户)
	// 3、ip：使用ip地址认证
	// 4、digest：即用户名:密码这种方式认证，最常用的  // 可以使用授权命令授权 add auth digest sa:123456
	public static void main(String[] args) throws Exception {
		CuratorFramework curatorFramework = CuratorClientUtils.getInstance();
		demo2(curatorFramework);
	}

	private static void demo2(CuratorFramework curatorFramework) throws Exception {
		curatorFramework.setACL().withACL(ZooDefs.Ids.CREATOR_ALL_ACL).forPath("/temp");
	}

	private static void demo1(CuratorFramework curatorFramework) throws Exception {
		List<ACL> list = new ArrayList<>();
		ACL acl = new ACL(ZooDefs.Perms.READ | ZooDefs.Perms.WRITE, new Id("digest",
				DigestAuthenticationProvider.generateDigest("admin:admin")));
		list.add(acl);
		curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(list)
				.forPath("/auth", "aaa".getBytes());
	}
}
