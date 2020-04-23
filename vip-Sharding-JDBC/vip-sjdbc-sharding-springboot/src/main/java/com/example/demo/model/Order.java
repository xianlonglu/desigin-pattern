package com.example.demo.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_order")
@Data
@Accessors(chain = true)
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long orderId;

    private Long userId;

}