package com.example.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Role;

public interface RoleMapper extends BaseMapper<Role> {

	Role findByName(@Param("name")String name);
}
