package com.lxl.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 消费者
 */
public class KafkaConsumerDemo1 extends Thread {

	private final KafkaConsumer kafkaConsumer;

	public KafkaConsumerDemo1(String topic) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKAF_BROKER_LIST);
		// properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"lxl-consumer");
		// 消费者组id
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumerDemo2");
		// 设置心跳时间properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,
		// "30000");

		// 是否自动提交
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		// properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		// 控制自动提交的频率。当然，我们也可以通过 consumer.commitSync()的方式实现手动提交
		// properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");

		// 对key和value设置反序列化对象
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		/*
		 * 这个参数是针对新的groupid中的消费者而言的，当有新 groupid的消费者来消费指定的topic时，对于该参数的配置，会有不同的语义
		 * auto.offset.reset=latest 情况下，新的消费者将会从其他消费者最后消费的offset处开始消费Topic下的消息；
		 * auto.offset.reset= earliest 情况下，新的消费者会从该 topic 最早的消息开始消费；
		 * auto.offset.reset=none 情况下，新的消费者加入以后，由于之前不存在offset，则会直接抛出异常。
		 */
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		kafkaConsumer = new KafkaConsumer(properties);
		// 订阅消费那些topic,消费指定分区的时候，不需要再订阅
		kafkaConsumer.subscribe(Collections.singletonList(topic));
		// 指定消费该 topic 下的 0 号分区。其他分区的数据就无法接收
		// TopicPartition topicPartition = new TopicPartition(topic, 0);
		// kafkaConsumer.assign(Arrays.asList(topicPartition));
	}

	@Override
	public void run() {
		while (true) {
			ConsumerRecords<String, String> consumerRecord = kafkaConsumer.poll(1000);
			consumerRecord.forEach(record -> {
				System.out.println("key:" + record.key() + "->value:" + record.value() + "->offset:" + record.offset()
						+ "->partition:" + record.partition());
				// 上边设置自动提交为false时。
				kafkaConsumer.commitAsync(); // 手动异步提交消费消息
				kafkaConsumer.commitSync(); // 手动同步提交消费消息
			});
		}
	}

	public static void main(String[] args) {
		new KafkaConsumerDemo1(KafkaProperties.TOPIC3).start();
	}
}
