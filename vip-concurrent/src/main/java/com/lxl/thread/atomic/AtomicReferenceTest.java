package com.lxl.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子更新引用类型:原子更新基本类型的AtomicInteger，只能更新一个变量，如果要原子更新多个变量，就需 要使用这个原子更新引用类型提供的类。
 * 
 * 1、AtomicReference：原子更新引用类型。 2、AtomicReferenceFieldUpdater：原子更新引用类型里的字段。
 * 3、AtomicMarkableReference：原子更新带有标记位的引用类型。可以原子更新一个布尔类
 * 型的标记位和引用类型。构造方法是AtomicMarkableReference（V initialRef，boolean initialMark）。
 * 
 * @author Administrator
 *
 */
public class AtomicReferenceTest {
	public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();

	public static void main(String[] args) {
		User user = new User("conan", 15);
		atomicUserRef.set(user);
		User updateUser = new User("Shinichi", 17);
		atomicUserRef.compareAndSet(user, updateUser);
		System.out.println(atomicUserRef.get().getName());
		System.out.println(atomicUserRef.get().getOld());
	}

	static class User {
		private String name;
		private int old;

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
