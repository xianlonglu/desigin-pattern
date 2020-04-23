package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
public class DemoApplication {
	// 更改启动图案 http://patorjk.com/software/taag/
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
