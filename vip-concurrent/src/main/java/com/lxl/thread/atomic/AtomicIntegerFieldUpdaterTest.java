package com.lxl.thread.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子更新字段类:如果需原子地更新某个类里的某个字段时，就需要使用原子更新字段类，Atomic包提供 了以下3个类进行原子字段更新
 * 
 * 1、tomicIntegerFieldUpdater：原子更新整型的字段的更新器。
 * 2、AtomicLongFieldUpdater：原子更新长整型字段的更新器。
 * 3、AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起
 * 来，可用于原子的更新数据和数据的版本号，可以解决使用CAS进行原子更新时可能出现的 ABA问题。
 * 
 * 要想原子地更新字段类需要两步。第一步，因为原子更新字段类都是抽象类，每次使用的时候必须使用静态方法newUpdater()创建一个更新器，
 * 并且需要设置想要更新的类和属性。第 二步，更新类的字段（属性）必须使用public volatile修饰符。
 * 
 * @author Administrator
 *
 */
public class AtomicIntegerFieldUpdaterTest {
	// 创建原子更新器，并设置需要更新的对象类和对象的属性
	private static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

	public static void main(String[] args) {
		// 设置柯南的年龄是10岁
		User conan = new User("conan", 10);
		// 柯南长了一岁，但是仍然会输出旧的年龄
		System.out.println(a.getAndIncrement(conan));
		// 输出柯南现在的年龄
		System.out.println(a.get(conan));
	}

	public static class User {
		private String name;
		public volatile int old;

		public User(String name, int old) {
			this.name = name;
			this.old = old;
		}

		public String getName() {
			return name;
		}

		public int getOld() {
			return old;
		}
	}
}
