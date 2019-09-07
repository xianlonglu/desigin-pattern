package com.lxl.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * partition规则设: 自定义数据分区规则(也就是发消息时根据key计算存储到那个分区：partition)
 */
public class MyPartition implements Partitioner {

	private Random random = new Random();

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		// 获得分区列表
		List<PartitionInfo> list = cluster.partitionsForTopic(topic);
		int size = list.size();
		int partitionNum = 0;
		if (key == null) {
			partitionNum = random.nextInt(size); // 随机分区
		} else {
			partitionNum = Math.abs((key.hashCode()) % size);
		}

		System.out.println("key ->" + key + "->value->" + value + "->" + partitionNum);
		return partitionNum; // 指定发送的分区值
	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> configs) {

	}

	public static void main(String[] args) {
		System.err.println(Math.abs(("KafkaConsumerDemo2".hashCode()) % 50));
	}
}
