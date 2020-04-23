package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.OrderItemMapper;
import com.example.demo.model.OrderItem;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;

/**
 * 绑定表的分库分表策略
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemShardingTest {
	@Autowired
	private OrderItemService service;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemMapper orderItemMapper;

	@Test
	public void insert() {
		List<OrderItem> entityList = new ArrayList<>();
		Long start = 1001L;
		Long length = start + 3;
		for (Long i = start; i <= length; i++) {
			entityList.add(new OrderItem().setItemId(i).setOrderId(i).setUserId(1111L));
		}
		start = 1101L;
		length = start  + 3;
		for (Long i = start; i <= length; i++) {
			entityList.add(new OrderItem().setItemId(i).setOrderId(i).setUserId(2222L));
		}
		service.saveBatch(entityList);
	}

	@Test
	public void select() {
		System.err.println("------bean1:" + service.getOne(new QueryWrapper<OrderItem>().eq("order_id", 107L).eq("user_id", 22L)));
		System.err.println("------bean2:" + service.getOne(new QueryWrapper<OrderItem>().eq("order_id", 108L).eq("user_id", 22L)));
		System.err.println("------bean3:" + service.getOne(new QueryWrapper<OrderItem>().eq("order_id", 111L).eq("user_id", 11L)));
		System.err.println("------bean4:" + service.getOne(new QueryWrapper<OrderItem>().eq("order_id", 112L).eq("user_id", 11L)));
	}

	@Test
	public void selectJoin() {
		System.err.println("------list:" + orderItemMapper.getOrderItem(107L, 22L));
	}

	/**
	 * RangeShardingAlgorithm是可选的，用于处理BETWEEN AND, >, <, >=, <=分片，
	 */
	@Test
	public void selectByRange() {
		List<OrderItem> list = service.list(new QueryWrapper<OrderItem>().between("item_id", 107L, 107L));
		System.err.println("------list:" + list);

	}

}
