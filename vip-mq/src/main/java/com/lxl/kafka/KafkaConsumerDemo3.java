package com.lxl.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * 消费者
 */
public class KafkaConsumerDemo3 extends Thread {

	private final KafkaConsumer kafkaConsumer;

	public KafkaConsumerDemo3(String topic) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKAF_BROKER_LIST);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"lxl-consumer");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumerDemo2");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		kafkaConsumer = new KafkaConsumer(properties);
		kafkaConsumer.subscribe(Collections.singletonList(topic));
		// TopicPartition topicPartition = new TopicPartition(topic, 0);
		// kafkaConsumer.assign(Arrays.asList(topicPartition));
	}

	@Override
	public void run() {
		while (true) {
			ConsumerRecords<String, String> consumerRecord = kafkaConsumer.poll(1000);
			for (ConsumerRecord record : consumerRecord) {
				System.out.println("key:" + record.key() + "->value:" + record.value() + "->offset:" + record.offset()
						+ "->partition:" + record.partition());
				kafkaConsumer.commitAsync();
			}
		}
	}

	public static void main(String[] args) {
		new KafkaConsumerDemo3(KafkaProperties.TOPIC3).start();
	}
}
