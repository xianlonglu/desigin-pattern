package com.gupaoedu;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gupaoedu.entity.UserInfo;
import com.gupaoedu.service.UserService;

/**
 * 演示取模的分库分表策略
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserShardingTest {
	@Resource
    UserService userService;

	/*@Test
	public void insert(){
		userService.insert();
	}*/

	@Test
	public void select(){
		/*UserInfo userInfo1= userService.getUserInfoByUserId(1L);
		System.out.println("------userInfo1:"+userInfo1);

		UserInfo userInfo2= userService.getUserInfoByUserId(2L);
		System.out.println("------userInfo2:"+userInfo2);
		 */
		
        List<UserInfo> userInfo3= userService.selectByRange(1L,12L);
        System.out.println("------userInfo3:"+userInfo3);
	}

}
