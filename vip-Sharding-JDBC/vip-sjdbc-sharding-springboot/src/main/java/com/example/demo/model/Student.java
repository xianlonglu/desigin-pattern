package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

//@TableName("student")
@Data
@Accessors(chain = true)
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

//	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	private String name;

	private String status;
}
