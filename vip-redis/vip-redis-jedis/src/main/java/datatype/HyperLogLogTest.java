package datatype;

import redis.clients.jedis.Jedis;

/**
 * Hyperloglogs：提供了一种不太准确的基数统计方法，比如统计网站的 UV，存在一定的误差。<br/>
 * pfadd, pfcount, pfmerge
 */
public class HyperLogLogTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);

		float size = 10000;

		for (int i = 0; i < size; i++) {
			jedis.pfadd("hll", "hll-" + i);
		}
		long total = jedis.pfcount("hll");
		System.out.println(String.format("统计个数: %s", total));
		System.out.println(String.format("正确率: %s", (total / size)));
		System.out.println(String.format("误差率: %s", 1 - (total / size)));
		jedis.close();
	}
}
