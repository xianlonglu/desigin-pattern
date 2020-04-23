package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.OrderItem;

public interface OrderItemMapper extends BaseMapper<OrderItem> {

	List<OrderItem> getOrderItem(@Param("orderId")Long orderId, @Param("userId")Long userId);
}