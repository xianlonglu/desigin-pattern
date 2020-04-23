package com.example.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_config")
@Data
@Accessors(chain = true)
public class Config {
    private Integer configId;

    private String paraName;

    private String paraValue;

    private String paraDesc;

   
}