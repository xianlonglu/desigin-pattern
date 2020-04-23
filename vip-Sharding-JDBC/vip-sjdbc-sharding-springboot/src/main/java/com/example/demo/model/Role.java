package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("role")
@Data
@Accessors(chain = true)
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

//	@TableId(value = "id", type = IdType.AUTO)
	private Long roleId;

	private String name;

	private String status;
	
	private Long userId;
}
