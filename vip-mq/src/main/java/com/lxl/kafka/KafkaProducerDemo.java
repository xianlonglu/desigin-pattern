package com.lxl.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 生产者
 */
public class KafkaProducerDemo extends Thread {

	private final KafkaProducer<String, String> producer;

	private final String topic;
	private final boolean isAysnc;

	public KafkaProducerDemo(String topic, boolean isAysnc) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKAF_BROKER_LIST);
		// 这个就是客户端的一个标识,可以用来跟踪请求
		properties.put(ProducerConfig.CLIENT_ID_CONFIG, "lxl-producer");
		/*
		 * acks配置表示producer发送消息到broker上以后的确认值。有三种确认方式（request.required.acks）
		 * 0：表示producer不需要等待broker的消息确认 1：表示producer只需要获得kafka集群中的leader节点确认即可
		 * all(-1)：需要ISR中所有的Replica给予接收确认，速度最慢，安全性最高，但是由于ISR可能会缩小到仅包含一个Replica，
		 * 所以设置参数为all并不能一定避免数据丢失。
		 */
		properties.put(ProducerConfig.ACKS_CONFIG, "-1");
		// 对key和value设置序列化对象
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
		// "org.apache.kafka.common.serialization.IntegerSerializer");
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
		// "org.apache.kafka.common.serialization.StringSerializer");
		// 自定义key分区规则
		properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartition.class.getName());
		producer = new KafkaProducer<String, String>(properties);

		this.topic = topic;
		this.isAysnc = isAysnc;
	}

	@Override
	public void run() {
		int num = 0;
		while (num < 20) {
			String msg = "message_" + num;
			System.out.print("begin send message:" + msg + "---");
			if (isAysnc) {// 异步发送//回调通知
//				producer.send(new ProducerRecord<String, String>(topic, num + "", msg), new Callback() {
//					@Override
//					public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//						if (recordMetadata != null) {
//							System.out.println("async-offset:" + recordMetadata.offset() + "->partition"
//									+ recordMetadata.partition());
//						}
//					}
//				});
				// java8语法   
				
				producer.send(new ProducerRecord<String, String>(topic, num + "", msg), (recordMetadata, exception) ->{
							if (recordMetadata != null) {
								System.out.println("async-topic:" + recordMetadata.topic() + "->partition:"
										+ recordMetadata.partition() + "->offset:" + recordMetadata.offset());
					}
				});
			} else {// 同步发送 future/callable
				try {
					//同步 get() -> Future()
					RecordMetadata recordMetadata = producer.send(
							new ProducerRecord<String, String>(topic, num + "", msg)).get();
					System.out.println("sync-offset:" + recordMetadata.offset() + "->partition"
							+ recordMetadata.partition());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

			}
			num++;

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new KafkaProducerDemo(KafkaProperties.TOPIC3, true).start();
	}
}
