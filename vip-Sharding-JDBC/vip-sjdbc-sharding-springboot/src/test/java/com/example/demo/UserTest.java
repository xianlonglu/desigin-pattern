package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserService service;

	@Test
	public void add() {
		List<User> entityList = new ArrayList<>();
		for (int i = 11; i < 13; i++) {
			User e = new User();
//			e.setId(Long.valueOf(i + ""));
			e.setName("name" + i);
			e.setStatus("status" + i);
			entityList.add(e);
		}
		System.err.println("entityList=" + service.saveBatch(entityList));
	}

	@Test
	public void list() {
		System.err.println("entityList1=" + service.list(new QueryWrapper<User>()));
		System.err.println("entityList2=" + service.list(new QueryWrapper<User>()));
		System.err.println("entityList3=" + service.list(new QueryWrapper<User>()));
		System.err.println("entityList4=" + service.list(new QueryWrapper<User>()));
	}

	@Test
	public void listmaster() {
		System.err.println("listmaster=" + service.listmaster());
	}

}
