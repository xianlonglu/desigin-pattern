package com.lxl.thread.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 模拟一大堆数据需要插入到数据库，我把它不断分解，直到每个任务最多只需插入两条数据
 * 
 * @author Administrator
 *
 */
public class BatchInsertTask extends RecursiveTask<Integer> {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ForkJoinPool forkJoinPool = new ForkJoinPool(100);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			list.add(i);

		}
		BatchInsertTask batchInsertTask = new BatchInsertTask(list);
		long t1 = System.currentTimeMillis();
		// Future<Integer> result = forkJoinPool.submit(batchInsertTask);
		ForkJoinTask<Integer> result = forkJoinPool.submit(batchInsertTask);
		System.err.println("result=" + result.get());
		long t2 = System.currentTimeMillis();
		System.err.println(t2 - t1);
	}

	// 要插入的数据
	List<Integer> records;

	public BatchInsertTask(List<Integer> records) {
		this.records = records;
	}

	@Override
	protected Integer compute() {
		// 当要插入的数据少于3，则直接插入
		if (records.size() < 11) {
			return computeDirectly();
		} else {
			// 如果要插入的数据大于等于3，则进行分组插入
			int size = records.size();

			// 第一个分组
			BatchInsertTask aTask = new BatchInsertTask(records.subList(0, size / 2));
			// 第二个分组
			BatchInsertTask bTask = new BatchInsertTask(records.subList(size / 2, records.size()));
			// 两个任务并发执行起来
			invokeAll(aTask, bTask);
			// 执行子任务
			// aTask.fork();
			// bTask.fork();

			// 两个分组的插入的行数加起来
			return aTask.join() + bTask.join();
		}
	}

	/**
	 * 真正插入数据的逻辑
	 */
	private int computeDirectly() {
		try {
			Thread.sleep((long) (records.size() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("插入了：" + Arrays.toString(records.toArray()));
		return records.size();
	}
}