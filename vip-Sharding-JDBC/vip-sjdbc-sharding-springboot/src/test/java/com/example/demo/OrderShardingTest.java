package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderShardingTest {
	@Autowired
	private OrderService service;

	@Test
	public void insert() {
		List<Order> entityList = new ArrayList<>();
		Long start = 1001L;
		Long length = start + 3;
		for (Long i = start; i <= length; i++) {
			entityList.add(new Order().setOrderId(i).setUserId(1111L));
		}
		start = 1001L;
		length = start + 3;
		for (Long i = start; i <= length; i++) {
			entityList.add(new Order().setOrderId(i).setUserId(2222L));
		}
		service.saveBatch(entityList);
	}

	@Test
	public void select() {
		System.err.println("------bean1:" + service.getOne(new QueryWrapper<Order>().eq("order_id", 107L).eq("user_id", 22L)));
		System.err.println("------bean2:" + service.getOne(new QueryWrapper<Order>().eq("order_id", 108L).eq("user_id", 22L)));
		System.err.println("------bean3:" + service.getOne(new QueryWrapper<Order>().eq("order_id", 111L).eq("user_id", 11L)));
		System.err.println("------bean4:" + service.getOne(new QueryWrapper<Order>().eq("order_id", 112L).eq("user_id", 11L)));
	}

	/**
	 * RangeShardingAlgorithm是可选的，用于处理BETWEEN AND, >, <, >=, <=分片，
	 */
	@Test
	public void selectByRange() {
		List<Order> list = service.list(new QueryWrapper<Order>().between("order_id", 107L, 107L));
		System.err.println("------list:" + list);

	}

}
