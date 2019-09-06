package com.gupaoedu.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消息消费者
 */
public class MyConsumer {
	private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
	private final static String QUEUE_NAME = "SIMPLE_QUEUE";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		// amqp://username:password@host:port/virtual host
		factory.setUri("amqp://guest:guest@127.0.0.1:5672");

		// 建立连接
		Connection conn = factory.newConnection();
		// 创建消息通道
		Channel channel = conn.createChannel();

		// 声明交换机
		// 1、String exchange 交换机名称
		// 2、String type：交换机的类型，direct, topic, fanout中的一种。
		// 3、boolean durable：是否持久化，代表交换机在服务器重启后是否还存在。
		// 4、boolean autoDelete 是否自动删除。
		// 如果为true，至少有一个消费者连接到这个队列，之后所有与这个队列连接的消费者都断开时，队列会自动删除。
		// 5、Map<String, Object> arguments：队列的其他属性，
		channel.exchangeDeclare(EXCHANGE_NAME, "direct", false, false, null);

		// 声明队列(队列名称、是否持久化、是否排他性队列、是否自动删除、队列的其他属性）详细见MyProducer.java中
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" Waiting for message....");

		// 绑定队列和交换机
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "lxl.test");

		// 创建消费者
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("Received message : '" + msg + "'");
				System.out.println("consumerTag : " + consumerTag);
				System.out.println("deliveryTag : " + envelope.getDeliveryTag());
			}
		};

		// 开始获取消息
		// String queue, boolean autoAck, Consumer callback
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
