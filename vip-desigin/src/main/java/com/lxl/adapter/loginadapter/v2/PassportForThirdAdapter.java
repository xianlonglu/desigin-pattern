package com.lxl.adapter.loginadapter.v2;

import java.lang.reflect.Method;

import com.lxl.adapter.loginadapter.ResultMsg;
import com.lxl.adapter.loginadapter.v1.service.SiginService;
import com.lxl.adapter.loginadapter.v2.adapters.LoginForQQAdapter;
import com.lxl.adapter.loginadapter.v2.adapters.LoginForTelAdapter;
import com.lxl.adapter.loginadapter.v2.adapters.LoginForTokenAdapter;
import com.lxl.adapter.loginadapter.v2.adapters.LoginForWechatAdapter;

/**
 * 结合策略模式、工厂模式、适配器模式
 */
public class PassportForThirdAdapter extends SiginService implements IPassportForThird {

    public ResultMsg loginForQQ(String id) {
//        return processLogin(id,RegistForQQAdapter.class);
        return processLogin(id,LoginForQQAdapter.class);
    }

    public ResultMsg loginForWechat(String id) {
        return processLogin(id,LoginForWechatAdapter.class);
    }

    public ResultMsg loginForToken(String token) {
        return processLogin(token,LoginForTokenAdapter.class);
    }

    public ResultMsg loginForTelphone(String telphone, String code) {
        return processLogin(telphone,LoginForTelAdapter.class);
    }

    public ResultMsg loginForRegist(String username, String passport) {
        super.regist(username,passport);
        return super.login(username,passport);
    }

    /*private ResultMsg processLogin(String key,Class<? extends LoginAdapter> clazz){
        try{
            //适配器不一定要实现接口
            LoginAdapter adapter = clazz.newInstance();

            //判断传过来的适配器是否能处理指定的逻辑
            if(adapter.support(adapter)){
                return adapter.login(key,adapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }*/
    
	/*
	 * 适配器模式跟策略模式好像区别不大？在这里我要强调一下，适配器模式主要解决的是功能兼容问题，单场景适配大家可能不会和策略模式有对比。
	 * 但多场景适配大家产生联想和混淆了。其实，大家有没有发现一个细节，我给每个适配器都加上了一个 support()方法，用来判断是否兼容，
	 * support()方法的参数也是 Object 的，而 supoort()来自于接口。适配器的实现逻辑并不依赖于接口，我们完全可以将
	 * LoginAdapter 接口去掉。而加上接口，只是为了代码规范。上面的代码可以说是策略模式、简单工厂模式和适配器模式的综合运用。
	 */
    //LoginForQQAdapter等类不实现LoginAdapter接口就需改成利用反射调用方法
    private ResultMsg processLogin(String key,Class<?> clazz){
        try{
            //适配器不一定要实现接口
            Object adapter = clazz.newInstance();

            //判断传过来的适配器是否能处理指定的逻辑
            Method method = clazz.getMethod("support", new Class[]{Object.class});
            if((boolean) method.invoke(adapter, adapter)){
                Method method1 = clazz.getMethod("login", new Class[]{String.class, Object.class});
                Object obj = method1.invoke(adapter,key,adapter);
            	return (ResultMsg)obj;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
