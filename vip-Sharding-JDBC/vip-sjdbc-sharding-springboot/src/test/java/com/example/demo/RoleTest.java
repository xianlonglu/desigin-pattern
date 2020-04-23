package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

/**
 * 单库分表
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {

	@Autowired
	private RoleService service;

	@Test
	public void add() {
		List<Role> entityList = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			Role e = new Role();
			e.setRoleId(Long.valueOf(i + ""));
			e.setName("name" + i);
			e.setStatus("status" + i);
			entityList.add(e);
		}
		System.err.println("entityList=" + service.saveBatch(entityList));
	}

	@Test
	public void list() {
		List<Role> list = service.list(new QueryWrapper<Role>().in("role_id", 3,7,6));
		System.err.println("list.size1=" + list.size());
		list = service.list(new QueryWrapper<Role>().in("role_id", 3,7,6));
		System.err.println("list.size2=" + list.size());
		HintManager.getInstance().setMasterRouteOnly();	
		list = service.list(new QueryWrapper<Role>().in("role_id", 3,7,6));
		System.err.println("list.size3=" + list.size());
	}

	@Test
	public void findByName() {
		//System.err.println("entityList=" + service.findByName("123"));
	}

}
