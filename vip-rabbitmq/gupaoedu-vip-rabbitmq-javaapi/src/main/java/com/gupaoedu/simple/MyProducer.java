package com.gupaoedu.simple;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ReturnListener;

/**
 * 消息生产者
 */
public class MyProducer {
	private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
	private final static String QUEUE_NAME = "ORIGIN_QUEUE";

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		// 连接IP
		factory.setHost("127.0.0.1");
		// 连接端口
		factory.setPort(5672);
		// 虚拟机
		factory.setVirtualHost("/");
		// 用户
		factory.setUsername("guest");
		factory.setPassword("guest");

		// amqp://username:password@host:port/virtual host
		// 解释：默认使用 / 的vhost，如果修改vhost，加在端口后即可，如 /gphost
		// factory.setUri("amqp://guest:guest@127.0.0.1:5672");// 相当于上边5个set

		// 建立连接
		Connection conn = factory.newConnection();
		// 创建消息通道
		Channel channel = conn.createChannel();

		channel.addReturnListener(new ReturnListener() {
			public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
					AMQP.BasicProperties properties, byte[] body) throws IOException {
				// 配合mandatory=true使用
				System.out.println("=========监听器收到了无法路由，被返回的消息============");
				System.out.println("replyText:" + replyText);
				System.out.println("exchange:" + exchange);
				System.out.println("routingKey:" + routingKey);
				System.out.println("message:" + new String(body));
			}
		});

		// 发送消息
		String msg = "Hello world, Rabbit MQ";

		// 声明队列
		// 1、String queue 队列名称
		// 2、boolean durable 是否持久化，代表队列在服务器重启后是否还存在。
		// 3、boolean exclusive 是否排他性队列。排他性队列只能在声明它的Connection中使用，连接断开时自动删除。
		// 4、boolean autoDelete 是否自动删除。
		// 如果为true，至少有一个消费者连接到这个队列，之后所有与这个队列连接的消费者都断开时，队列会自动删除。
		// 5、Map<String, Object> arguments：队列的其他属性，
		// 例如x-message-ttl、x-expires、x-max-length、x-maxlength-bytes、x-dead-letter-exchange、x-dead-letter-routing-key、x-max-priority。
		Map<String, Object> argss = new HashMap<String, Object>();
		argss.put("x-message-ttl", 6000); // 通过队列属性设置消息过期时间：
		argss.put("x-expires", 6000); // 队列的过期时间,决定了在没有任何消费者以后，队列可以存活多久。
		argss.put("x-max-priority", 10); // 队列最大优先级
		channel.queueDeclare(QUEUE_NAME, false, false, false, argss);

		// 消息属性BasicProperties,参数 释义
		// 1、Map<String,Object> headers 消息的其他自定义参数
		// 2、Integer deliveryMode 2持久化，其他：瞬态
		// 3、Integer priority 消息的优先级
		// 4、String correlationId 关联ID，方便RPC相应与请求关联
		// 5、String replyTo 回调队列
		// 6、String expiration TTL，消息过期时间，单位毫秒
		AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2) // 持久化消息
				.contentEncoding("UTF-8").expiration("10000") // TTL
				.priority(5) // 消息优先级，优先级高的消息可以优先被消费，但是：只有消息堆积（消息的发送速度大于消费者的消费速度）的情况下优先级才有意义。
				.build();
		// 发送消息（发送到默认交换机AMQP Default，Direct）
		// 如果有一个队列名称跟Routing Key相等，那么消息会路由到这个队列
		// exchange 交换机名称；routingKey 路由关键字；BasicProperties props 参数、属性；byte[]
		// body 消息
		// 发送到了默认的交换机上，由于没有任何队列使用这个关键字跟交换机绑定，所以会被退回
		// 第三个参数是设置的mandatory，如果mandatory是false，消息也会被直接丢弃
		channel.basicPublish(EXCHANGE_NAME, "lxl.test", true, properties, msg.getBytes());

		TimeUnit.SECONDS.sleep(10);
		channel.close();
		conn.close();
	}
}
