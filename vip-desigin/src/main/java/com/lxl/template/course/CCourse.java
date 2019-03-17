package com.lxl.template.course;

public class CCourse extends NetworkCourse {

	private boolean flag = false;

	public CCourse(boolean flag) {
		this.flag = flag;
	}

	@Override
	protected boolean needHomework() {
		 return this.flag;
	}

	@Override
	void checkHomework() {
		System.out.println("检查c课程");
	}

}
