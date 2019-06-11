package com.lxl.thread.synchronized1;

/**
 * Wait/Notify实例
 * @author Administrator
 *
 */
public class ThreadWait extends Thread{
	private Object lock;

	public ThreadWait(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized (lock) {
			System.out.println("开始执行 thread wait");
			try {
				lock.wait();//实现线程的阻塞
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("执行结束 thread wait");
		}
	}
	public static void main(String[] args) {
		Object lock = 1 ;
		new ThreadWait(lock).start();
		new ThreadNotify(lock).start();
	}
}
