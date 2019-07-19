package com.lxl.io.nio.buffer;

import java.io.FileInputStream;
import java.nio.*;
import java.nio.channels.*;

/**
 * 我们说缓冲区对象本质上是一个数组，但它其实是一个特殊的数组，缓冲区对象内置了一些机制， 能够跟踪和记录缓冲区的状态变化情况，如果我们使用
 * get()方法从缓冲区获取数据或者使用 put()方法把数据写入缓冲 区，都会引起缓冲区状态的变化。
 * 
 * position：指定下一个将要被写入或者读取的元素索引，它的值由 get()/put()方法自动更新，在新创建一个 Buffer
 * 对象时，position 被初始化为 0。
 * limit：指定还有多少数据需要取出(在从缓冲区写入通道时)，或者还有多少空间可以放入数据(在从通道读入缓冲区时)。
 * capacity：指定了可以存储在缓冲区中的最大数据容量，实际上，它指定了底层数组的大小，或者至少是指定了准许我 们使用的底层数组的容量。
 * 以上三个属性值之间有一些相对大小的关系：0 <= position <= limit <= capacity。
 * 
 * @author Administrator
 *
 */
public class BufferDemo {
	public static void main(String args[]) throws Exception {
		// 这用用的是文件IO处理
		FileInputStream fin = new FileInputStream("D://test.txt");
		// 创建文件的操作管道
		FileChannel fc = fin.getChannel();

		// 使用allocateDirect就是直接缓冲区见DirectBuffer.java
		//ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  
		// 分配一个10个大小缓冲区，说白了就是分配一个10个大小的byte数组
		ByteBuffer buffer = ByteBuffer.allocate(10);
		output("初始化", buffer);

		// 先读一下
		fc.read(buffer);
		output("调用read()", buffer);

		// 准备操作之前，先锁定操作范围
		buffer.flip();
		output("调用flip()", buffer);

		// 判断有没有可读数据
		while (buffer.remaining() > 0) {
			byte b = buffer.get();
			// System.out.print(((char)b));
		}
		output("调用get()", buffer);

		// 可以理解为解锁
		buffer.clear();
		output("调用clear()", buffer);

		// 最后把管道关闭
		fin.close();
	}

	// 把这个缓冲里面实时状态给答应出来
	public static void output(String step, ByteBuffer buffer) {
		System.out.print(step + " : ");
		// 容量，数组大小
		System.out.print("capacity: " + buffer.capacity() + ", ");
		// 当前操作数据所在的位置，也可以叫做游标
		System.out.print("position: " + buffer.position() + ", ");
		// 锁定值，flip，数据操作范围索引只能在position - limit 之间
		System.out.println("limit: " + buffer.limit());
	}
}