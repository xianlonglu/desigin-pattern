package com.lxl.thread.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 利用多线程解决一个实际问题。具体的问题可以结合大家以往项目中遇到过的问题进行优化。
 * 
 * 要求：(1):通过图形方式表达使用前后的差异； (2):代码实现
 * 
 * @author Administrator
 *
 */
public class word1 {

	public static boolean doThing() {
		// 模拟业务执行时间
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		long time1 = new Date().getTime();

		for (int i = 0; i < 5; i++) {
			doThing();
		}
		long time2 = new Date().getTime();

		System.out.println("耗时：" + (time2 - time1) + "ms");
		concurrent();
		long time3 = new Date().getTime();
		System.out.println("耗时：" + (time3 - time2) + "ms");
	}

	private static void concurrent() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Future<Date>> list = new ArrayList<Future<Date>>();
		for (int i = 0; i < 3; i++) {
			Future<Date> future = executorService.submit(new Callable<Date>() {
				@Override
				public Date call() throws Exception {
					doThing();
					return new Date();
				}

			});
			list.add(future);
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).get());
		}
	}
}
