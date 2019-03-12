package com.lxl.prototype.work;

import java.util.ArrayList;
import java.util.List;

public class DeepCloneTest {

	public static void main(String[] args) throws CloneNotSupportedException {

		Grade grade1 = new Grade();
		grade1.setGradeName("年级");
		List courseList1 = new ArrayList();
		for (int i = 1; i < 11; i++) {
			Course course = new Course();
			course.setCourseName("年级课程" + i);
			courseList1.add(course);
		}
		grade1.setCourseList(courseList1);

		List<Grade> list = new ArrayList<Grade>();
		for (int j = 1; j < 4; j++) {
			Grade grade = (Grade) grade1.clone();
			grade.setGradeName("年级" + j);
			List courseList = new ArrayList();
			for (int i = 1; i < 11; i++) {
				Course course = new Course();
				course.setCourseName(j + "年级课程" + i);
				courseList.add(course);
			}
			grade.setCourseList(courseList);
			list.add(grade);
		}
		for (int j = 0; j < list.size(); j++) {
			Grade grade = list.get(j);
			System.out.println(grade.getGradeName() + "的课程："
					+ grade.getCourseList());
		}

		try {
			Grade grade3 = (Grade) grade1.clone();
			System.out.println("深克隆："
					+ (grade1.courseList == grade3.courseList));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 给每个学生都发一份试卷
		/*
		 * Student student=new Student(); List<Student> list=new
		 * ArrayList<Student>(); student.setTestPaper(1); for (int
		 * i=0;i<50;i++){ Student stu=student.clone();
		 * stu.setName("我是第"+i+"个学生"); list.add(stu); } for(int
		 * i=0;i<list.size();i++){ Student stu=list.get(i);
		 * System.out.println(stu.getName()+"试卷"+stu.getTestPaper()); }
		 */

	}
}
