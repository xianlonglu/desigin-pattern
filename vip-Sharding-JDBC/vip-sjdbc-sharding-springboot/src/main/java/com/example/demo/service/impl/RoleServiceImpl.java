package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.RoleMapper;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Override
	public Role findByName(String name) {
		System.err.println(" 执行  findByName 方法");
		return baseMapper.selectOne(new QueryWrapper<Role>().eq("name", name));
	}

}
