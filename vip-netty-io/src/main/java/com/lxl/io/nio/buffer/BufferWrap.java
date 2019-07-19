package com.lxl.io.nio.buffer;

import java.nio.ByteBuffer;

/**
 * 手动分配缓冲区，在创建一个缓冲区对象时，会调用静态方法 allocate()来指定缓冲区的容量，其实调用
 * allocate()相当于创建了一个指定大小的数组，并把它包装为缓冲区对象。或者我们也可以直接将一个现有的数组，包装为缓冲区对 象，
 */
public class BufferWrap {

	public static void main(String[] args) {
		// 分配指定大小的缓冲区
		ByteBuffer buffer1 = ByteBuffer.allocate(10);
		while (buffer1.remaining() > 0) {
			System.out.print(buffer1.get() + " ");
		}

		// 包装一个现有的数组
//		byte[] array = new byte[10];
		byte[] array = {1,2,3,4,5};
		ByteBuffer buffer2 = ByteBuffer.wrap(array);
		while (buffer2.remaining() > 0) {
			System.err.print(buffer2.get() + " ");
		}
	}
}