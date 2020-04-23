package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.model.Role;

public interface RoleService extends IService<Role>{

	Role findByName(String name);
	
}
