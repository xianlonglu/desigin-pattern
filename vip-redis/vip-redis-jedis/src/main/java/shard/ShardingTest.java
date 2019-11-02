package shard;

import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;

/**
 * Jedis 客户端提供了 Redis Sharding 的方案，并且支持连接池。<br/>
 * 使用 ShardedJedis 之类的客户端分片代码的优势是配置简单，不依赖于其他中间件，分区的逻辑可以自定义，比较灵活。<br/>
 * 但是基于客户端的方案，不能实现动态的服务增减，每个客户端需要自行维护分片策略，存在重复代码。<br/>
 * 第二种思路就是把分片的代码抽取出来，做成一个公共服务，所有的客户端都连接 到这个代理层。由代理层来实现请求和转发。
 */
public class ShardingTest {
	public static void main(String[] args) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();

		// Redis服务器
		JedisShardInfo shardInfo1 = new JedisShardInfo("127.0.0.1", 6379);
		JedisShardInfo shardInfo2 = new JedisShardInfo("192.168.8.205", 6379);

		// 连接池
		List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2);
		ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, infoList);

		ShardedJedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			for (int i = 0; i < 100; i++) {
				jedis.set("k" + i, "" + i);
			}
			for (int i = 0; i < 100; i++) {
				Client client = jedis.getShard("k" + i).getClient();
				System.out.println("取到值：" + jedis.get("k" + i) + "，" + "当前key位于：" + client.getHost() + ":"
						+ client.getPort());
			}

		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
