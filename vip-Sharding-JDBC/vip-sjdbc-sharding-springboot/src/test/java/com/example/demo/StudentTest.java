package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

	@Autowired
	private StudentService service;

	@Test
	public void add() {
		List<Student> entityList = new ArrayList<>();
		for (int i = 111; i < 131; i++) {
			Student e = new Student();
//			e.setId(Long.valueOf(i + ""));
			e.setName("name" + i);
			e.setStatus("status" + i);
			entityList.add(e);
		}
		System.err.println("entityList=" + service.saveBatch(entityList));
	}

	@Test
	public void list() {
		System.err.println("entityList1=" + service.list(new QueryWrapper<Student>()));
		System.err.println("entityList2=" + service.list(new QueryWrapper<Student>()));
		System.err.println("entityList3=" + service.list(new QueryWrapper<Student>()));
		System.err.println("entityList4=" + service.list(new QueryWrapper<Student>()));
	}

}
