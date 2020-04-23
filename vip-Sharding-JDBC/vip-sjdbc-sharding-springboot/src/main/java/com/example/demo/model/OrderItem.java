package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_order_item")
@Data
@Accessors(chain = true)
public class OrderItem {
    private Long itemId;

    private Long orderId;

    private Long userId;

}