package com.lxl.adapter.loginadapter.v1.service;

import com.lxl.adapter.loginadapter.ResultMsg;
import com.lxl.template.jdbc.Member;

/**
 * Created by Tom.
 */
public class SiginService {

    /**
     * 注册方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg regist(String username,String password){
    	System.err.println(username + "注册成功,密码=" + password + ",Member=" + new Member());
        return  new ResultMsg(200,"注册成功",new Member());
    }


    /**
     * 登录的方法
     * @param username
     * @param password
     * @return
     */
    public ResultMsg login(String username,String password){
    	System.err.println(username + "登陆成功,密码=" + password + ",Member=" + new Member());
        return new ResultMsg(200,"登陆成功",new Member());
    }

}
