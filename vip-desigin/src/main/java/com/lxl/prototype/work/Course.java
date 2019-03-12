package com.lxl.prototype.work;

import java.io.Serializable;

/**
 * 课程类
 * @author Administrator
 *
 */
public class Course implements Serializable{

	private String courseName;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + "]";
	}

}
