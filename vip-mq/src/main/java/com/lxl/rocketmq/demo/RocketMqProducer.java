package com.lxl.rocketmq.demo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class RocketMqProducer {

	public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException,
			MQBrokerException {
		/**
		 * 生产者组，简单来说就是多个发送同一类消息的生产者称之为一个生产者组
		 * rocketmq支持事务消息，在发送事务消息时，如果事务消息异常（producer挂了），broker端会来回查
		 * 事务的状态，这个时候会根据group名称来查找对应的producer来执行相应的回查逻辑。相当于实现了 producer的高可用
		 */
		DefaultMQProducer producer = new DefaultMQProducer("gp_producer_group");
		producer.setNamesrvAddr("192.168.13.102:9876"); // 它会从命名服务器上拿到broker的地址
		producer.start();

		int num = 0;
		while (num < 20) {
			num++;
			// Topic
			// tags -> 标签 （分类） -> (筛选)
			Message message = new Message("gp_test_topic", "TagA", ("Hello , RocketMQ:" + num).getBytes());

			// 消息路由策略
			producer.send(message, new MessageQueueSelector() {
				@Override
				public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
					return list.get(0);
				}
			}, "key-" + num);
			/*
			 * SendResult中，有一个sendStatus状态，表示消息的发送状态。一共有四种状态
			 * 
			 * 1. FLUSH_DISK_TIMEOUT：表示没有在规定时间内完成刷盘（需要Broker 的刷盘策lll创立设置成
			 * SYNC_FLUSH 才会报这个错误） 。
			 * 
			 * 2. FLUSH_SLAVE_TIMEOUT：表示在主备方式下，并且Broker 被设置成SYNC_MASTER 方式，没有
			 * 在设定时间内完成主从同步。
			 * 
			 * 3. SLAVE_NOT_AVAILABLE： 这个状态产生的场景和FLUSH_SLAVE_TIMEOUT 类似， 表示在主备方
			 * 式下，并且Broker 被设置成SYNC_MASTER ，但是没有找到被配置成Slave 的Broker 。
			 * 
			 * 4. SEND OK：表示发送成功，发送成功的具体含义，比如消息是否已经被存储到磁盘？消息是否被 同步到了Slave
			 * 上？消息在Slave 上是否被写入磁盘？需要结合所配置的刷盘策略、主从策略来
			 * 定。这个状态还可以简单理解为，没有发生上面列出的三个问题状态就是SEND OK
			 */
			// 发送消息并且获取发送结果
			// SendResult sendResult = producer.send(message);
		}
	}
}
