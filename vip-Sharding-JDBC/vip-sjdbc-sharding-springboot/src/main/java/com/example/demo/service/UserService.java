package com.example.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.User;

public interface UserService extends IService<User>{

	/**
	 * 强制路由主库
	 * @return
	 */
	List<User> listmaster();

}
