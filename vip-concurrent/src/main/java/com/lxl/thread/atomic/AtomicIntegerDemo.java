package com.lxl.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * juc中的原子操作：原子更新基本类型类 1、AtomicBoolean：原子更新布尔类型。2、AtomicInteger：原子更新整型。
 * 3、AtomicLong：原子更新长整型。 AtomicInteger的常用方法如下。
 * 
 * 1、int addAndGet（int delta）：以原子方式将输入的数值与实例中的值（AtomicInteger里的 value）相加，并返回结果。
 * 
 * 2、boolean compareAndSet（int expect，int update）：如果输入的数值等于预期值，则以原子方
 * 式将该值设置为输入的值。
 * 
 * 3、int getAndIncrement()：以原子方式将当前值加1，注意，这里返回的是自增前的值。
 * 
 * 4、void lazySet（int newValue）：最终会设置成newValue，使用lazySet设置值后，可能导致其他
 * 线程在之后的一小段时间内还是可以读到旧的值。
 * 
 * 5、int getAndSet（int newValue）：以原子方式设置为newValue的值，并返回旧值。
 * 
 * @author Administrator
 *
 */
public class AtomicIntegerDemo {
	private static AtomicInteger count = new AtomicInteger(0);

	public static synchronized void inc() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count.getAndIncrement();
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			new Thread(AtomicIntegerDemo::inc).start();
			// new Thread(() -> AtomicIntegerDemo.inc()).start();
		}
		Thread.sleep(400);
		System.err.println(count.get());

		System.out.println(count.getAndIncrement());
		System.out.println(count.get());
	}
}
