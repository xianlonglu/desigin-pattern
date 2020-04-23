package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("user_info")
@Data
@Accessors(chain = true)
public class UserInfo {
    private Long userId;
    private String userName;
    private String account;
    private String password;

}
