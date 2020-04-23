package com.gupao.gpjvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GpJvmApplication {
	// 为了测试对内存溢出设置堆内存大小: -Xms20M -Xmx20M
	// (4)要是在发生堆内存溢出的时候，能自动dump出该文件就好了
	// 一般在开发中，JVM参数可以加上下面两句，这样内存溢出时，会自动dump出该文件
	// -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.hprof
	// 
	// 为了测试对内存溢出设置方法区内存大小:-XX:MetaspaceSize=50M -XX:MaxMetaspaceSize=50M
	// 
	// 打印GC日志:	-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:gc.log
	// 
	// 

	public static void main(String[] args) {
		SpringApplication.run(GpJvmApplication.class, args);
	}
}
