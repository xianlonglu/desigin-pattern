package com.gupaoedu;

import com.gupaoedu.entity.Order;
import com.gupaoedu.entity.OrderItem;
import com.gupaoedu.service.OrderItemService;
import com.gupaoedu.service.OrderService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 绑定表的分库分表策略
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.gupaoedu.mapper")
public class OrderItemShardingTest {
	@Resource
	OrderItemService orderItemService;
	@Resource
	OrderService orderService;

	@Test
	public void insert() {
		orderService.insert();
		orderItemService.insert();
	}

	@Test
	public void select1() {
		OrderItem orderItem1 = orderItemService.getOrderItemByItemId(1);
		System.out.println("------orderItem1:" + orderItem1);

		OrderItem orderItem2 = orderItemService.getOrderItemByItemId(2);
		System.out.println("------orderItem2:" + orderItem2);
	}

	@Test
	public void select2() {
		Order order1 = orderService.getOrderInfoByOrderId(1);
		System.out.println("------order1:" + order1);

		Order order2 = orderService.getOrderInfoByOrderId(2);
		System.out.println("------order2:" + order2);
	}
}
