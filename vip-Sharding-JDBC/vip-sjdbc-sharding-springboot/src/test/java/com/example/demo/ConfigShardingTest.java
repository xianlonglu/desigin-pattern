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
import com.example.demo.model.Config;
import com.example.demo.service.ConfigService;

/**
 * 广播表的分库分表策略
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigShardingTest {
	@Autowired
	private ConfigService service;

	@Test
	public void insert() {
		List<Config> entityList = new ArrayList<>();
		Integer start = 101;
		Integer length = start + 1;
		for (int i = start; i <= length; i++) {
			entityList.add(new Config().setConfigId(i).setParaName("name" + i).setParaValue("value" + i).setParaDesc("desc" + i));
		}
		service.saveBatch(entityList);
	}

	@Test
	public void update() {
		Config config = new Config();
		config.setParaDesc("test");
		service.update(config, new UpdateWrapper<Config>().eq("config_id", 1));
	}

	@Test
	public void select() {
		System.err.println("------bean1:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
		System.err.println("------bean2:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
		System.err.println("------bean3:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
		System.err.println("------bean4:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
		System.err.println("------bean5:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
		System.err.println("------bean6:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
		System.err.println("------bean7:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
		System.err.println("------bean8:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
		System.err.println("------bean9:" + service.getOne(new QueryWrapper<Config>().eq("config_id", 1)));
	}

}
