package com.lxl.thread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 当一个线程需要等待另一个线程把某个任务执行完后它才能继续执行，此时可以使用
 * FutureTask。假设有多个线程执行若干任务，每个任务最多只能被执行一次。当多个线程试图
 * 同时执行同一个任务时，只允许一个线程执行任务，其他线程需要等待这个任务执行完后才 能继续执行。
 * 
 * 当两个线程试图同时执行同一个任务时，如果Thread 1执行1.3后Thread 2执行2.1，那么接下来Thread 2将在2.2等待，直到Thread
 * 1执行完1.4后Thread 2才能从2.2（FutureTask.get()）返回。
 * 
 * @author Administrator
 *
 */
public class FutureTaskDemo {
	private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<Object, Future<String>>();

	private String executionTask(final String taskName) throws ExecutionException,
			InterruptedException {
		while (true) {
			Future<String> future = taskCache.get(taskName); // 1.1,2.1
			if (future == null) {
				Callable<String> task = new Callable<String>() {
					public String call() throws InterruptedException {
						System.err.println("taskName=" + taskName);
						return taskName;
					}
				}; // 1.2创建任务
				FutureTask<String> futureTask = new FutureTask<String>(task);
				future = taskCache.putIfAbsent(taskName, futureTask); // 1.3
				if (future == null) {
					future = futureTask;
					futureTask.run(); // 1.4执行任务
				}
			}
			try {
				return future.get(); // 1.5,2.2线程在此等待任务执行完成
			} catch (CancellationException e) {
				taskCache.remove(taskName, future);
			}
		}
	}
	
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTaskDemo futureTaskDemo= new FutureTaskDemo();
		FutureDemo demo = new FutureDemo();
		futureTaskDemo.executionTask("taskName" + 0);
		for (int i = 0; i < 5; i++) {
			System.out.println(futureTaskDemo.executionTask("taskName" + 1));
			Thread.sleep(1000);
		}
		System.err.println(futureTaskDemo.taskCache.size());
	}
}
