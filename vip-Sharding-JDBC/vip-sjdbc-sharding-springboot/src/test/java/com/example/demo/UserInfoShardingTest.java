package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.model.UserInfo;
import com.example.demo.service.UserInfoService;

/**
 * 多库分表
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoShardingTest {
	@Autowired
	private UserInfoService service;

	@Test
	public void insert() {
		List<UserInfo> entityList = new ArrayList<>();
		Integer start = 101;
		Integer length = start + 3;
		for (int i = start; i <= length; i++) {
//			entityList.add(new UserInfo().setUserId(Long.valueOf(i + "")).setUserName("name" + i)
//					.setAccount("account" + i).setPassword("pwd" + i));
			entityList.add(new UserInfo().setUserName("name" + i).setAccount("acc" + i).setPassword("pwd" + i));
		}
		service.saveBatch(entityList);
	}

	@Test
	public void update() {
		UserInfo userInfo = new UserInfo();
		userInfo.setPassword("password111");
		service.update(userInfo, new UpdateWrapper<UserInfo>().eq("user_id", 1));
	}

	@Test
	public void select() {
		System.err.println("------bean1:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 1L)));
		System.err.println("------bean2:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 2L)));
		System.err.println("------bean3:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 3L)));
		System.err.println("------bean4:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 4L)));
//		System.err.println("------bean5:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 5)));
//		System.err.println("------bean6:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 6)));
//		System.err.println("------bean7:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 7)));
//		System.err.println("------bean8:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 8)));
//		System.err.println("------bean9:" + service.getOne(new QueryWrapper<UserInfo>().eq("user_id", 9)));
	}

	/**
	 * RangeShardingAlgorithm是可选的，用于处理BETWEEN AND, >, <, >=, <=分片，
	 */
	@Test
	public void selectByRange() {
		List<UserInfo> list = service.list(new QueryWrapper<UserInfo>().between("user_id", 1L, 1L));
		System.err.println("------list:" + list);

	}

}
