package com.lxl.prototype.work;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * 年级类
 * 
 * @author Administrator
 *
 */
public class Grade implements Cloneable, Serializable {
	private Integer id;
	private String GradeName;
	List<Course> courseList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGradeName() {
		return GradeName;
	}

	public void setGradeName(String gradeName) {
		GradeName = gradeName;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return deepClone();
	}

	/**
	 * 深克隆
	 * 
	 * @return
	 */
	public Object deepClone() {
		try {

			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			oout.writeObject(this);

			ByteArrayInputStream bin = new ByteArrayInputStream(
					bout.toByteArray());
			ObjectInputStream oin = new ObjectInputStream(bin);
			return oin.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
