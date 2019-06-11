package com.lxl.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新数组：1、AtomicIntegerArray：原子更新整型数组里的元素。2、AtomicLongArray：原子更新长整型数组里的元素。 3、
 * AtomicReferenceArray：原子更新引用类型数组里的元素。
 * 
 * AtomicIntegerArray类主要是提供原子的方式更新数组里的整型，其常用方法如下。
 * 
 * 1、int addAndGet（int i，int delta）：以原子方式将输入值与数组中索引i的元素相加。
 * 
 * 2、boolean compareAndSet（int i，int expect，int update）：如果当前值等于预期值，则以原子
 * 方式将数组位置i的元素设置成update值
 * 
 * @author Administrator
 *
 */
public class AtomicIntegerArrayTest {
	static int[] value = new int[] { 1, 2 };
	static AtomicIntegerArray ai = new AtomicIntegerArray(value);

	public static void main(String[] args) {
		ai.getAndSet(0, 3);
		System.out.println(ai.get(0));
		System.out.println(value[0]);
		/*
		 * 需要注意的是，数组value通过构造方法传递进去，然后AtomicIntegerArray会将当前数组
		 * 复制一份，所以当AtomicIntegerArray对内部的数组元素进行修改时，不会影响传入的数组。
		 */
	}
}
