package com.example.demo.service.impl;

import java.util.List;

import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public List<User> listmaster() {
		HintManager.getInstance().setMasterRouteOnly();		
		return baseMapper.selectList(new QueryWrapper<User>());
	}

}
