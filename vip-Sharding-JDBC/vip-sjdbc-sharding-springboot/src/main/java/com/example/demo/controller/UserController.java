package com.example.demo.controller;

import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public Object list() {
		HintManager.getInstance().setMasterRouteOnly();
		return userService.list();
	}
	
	@GetMapping("/add")
	public Object add() {
		for (long i = 31; i <= 60; i++) {
			User user = new User();
			user.setId(i);
			user.setName("李四");
			userService.save(user);
		}
		return "success";
	}
	
	@GetMapping("/users/{id}")
	public Object get(@PathVariable Long id) {
		HintManager.getInstance().setMasterRouteOnly();
		return userService.getById(id);
	}
	
	@GetMapping("/users/query")
	public Object get(String name) {
		return userService.getOne(new QueryWrapper<User>().eq("name", name));
	}

	
}
