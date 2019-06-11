package com.lxl.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserService {
	private final ExecutorService single = Executors.newSingleThreadExecutor();
	private volatile boolean isRunning = true;
	ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);
	{
		init();
	}

	public void init() {
		single.execute(() -> {
			while (isRunning) {
				try {
					User user = (User) arrayBlockingQueue.take();// 阻塞的方式获取队列中的数据

					sendPoints(user);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		new UserService().register();
	}

	public boolean register() {
		User user = new User();
		user.setName("Mic");
		addUser(user);
		arrayBlockingQueue.add(user);// 添加到异步 队列
		return true;
	}

	private void addUser(User user) {
		System.out.println("添加用户：" + user);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendPoints(User user) {
		System.out.println(" 发 送 积 分 给 指 定 用 户:" + user);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class User {
	private String name;
	public int old;

	public User() {
	}

	public User(String name, int old) {
		this.name = name;
		this.old = old;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

}
