package com.lxl.template.course;

public class NetworkCourseTest {
    public static void main(String[] args) {

        System.out.println("---Java架构师课程---");
        NetworkCourse javaCourse = new JavaCourse();
        javaCourse.createCourse();

        System.out.println("---大数据课程---");
        NetworkCourse bigDataCourse = new BigDataCourse(true);
        bigDataCourse.createCourse();
        
        System.out.println("---c课程---");
        CCourse cCourse = new CCourse(true);
        cCourse.createCourse();

    }
}
