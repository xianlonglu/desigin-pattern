package com.lxl.rocketmq.demo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class RocketMqConsumer {
	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("gp_consumer_group");
		// 指定NameServer地址，多个地址以 ; 隔开
		consumer.setNamesrvAddr("192.168.13.102:9876");
		// 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
		// 如果非第一次启动，那么按照上次消费的位置继续消费
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		// 订阅PushTopic下Tag为push的消息
		// *表示不过滤，可以通过tag来过滤，比 如:”tagA”
		consumer.subscribe("gp_test_topic", "*");

		/**
		 * 注册消息监听回调 这里有两种监听，MessageListenerConcurrently以及MessageListenerOrderly
		 * 前者是普通监听，后者是顺序监听。这块在后续单独说明
		 */
		// consumer.registerMessageListener(new MessageListenerConcurrently() {
		// @Override
		// public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt>
		// list,
		// ConsumeConcurrentlyContext consumeConcurrentlyContext) {
		// System.out.println("Receive Message: "+list);
		// System.out.printf("%s Receive New Messages: %s %n",
		// Thread.currentThread().getName(), list);
		// return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //签收
		// }
		// });

		consumer.registerMessageListener(new MessageListenerOrderly() {
			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list,
					ConsumeOrderlyContext consumeOrderlyContext) {

				MessageExt messageExt = list.get(0);
				// TODO --
				// Throw Exceptio
				// 重新发送该消息
				// DLQ（通用设计）
				if (messageExt.getReconsumeTimes() == 3) { // 消息重发了三次
					// 持久化 消息记录表
					return ConsumeOrderlyStatus.SUCCESS; // 签收
				}
				return ConsumeOrderlyStatus.SUCCESS; // 签收
			}
		});

		consumer.start();

	}
}
