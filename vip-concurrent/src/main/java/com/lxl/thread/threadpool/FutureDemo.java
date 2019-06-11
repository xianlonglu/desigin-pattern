package com.lxl.thread.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
 * 带返回值的 线程实现方式 ：实现 Callable 接口通过 FutureTask 包装器来创建 Thread 线程
 * 
 * 这个在很多地方有用到，比如 Dubbo 的异步调用，比如消息中间件的异步通信等等… 利用 FutureTask、Callable、Thread
 * 对耗时任务（如查询数据库）做预处理，在需要计算结 果之前就启动计算。
 * 
 * @author Administrator
 *
 */
public class FutureDemo implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("execute:call");
		Thread.sleep(1000);
		int a = 1;
		int b = 2;
		return "执行结果:" + (a + b);
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureDemo futureDemo = new FutureDemo();
		/**
		 * FutureTask 是 Runnable 和 Future 的结合，如果我们把 Runnable 比作是生产者，Future
		 * 比作是消费者， 那么 FutureTask 是被这两者共享的， 生产者运行 run 方法计算结果，消费者通过 get 方法获取结果。
		 * 作为生产者消费者模式，有一个很重要的机制，就是如果生产者数据还没准备的时候，消费者会被阻塞。当生产者数据准备好了以后会唤醒消费者继续执行。
		 */
		/**
		 * 主线程也可以执行futureTask.cancel（boolean mayInterruptIfRunning）来取消此任务的执行。
		 * futureTask.cancel(true);
		 */
		FutureTask futureTask = new FutureTask(futureDemo);
		// new Thread(futureTask).start();
		// System.out.println(futureTask.get()); // 阻塞获取结果

		// 2、线程池对于 Future/Callable 的执行。线程池里面的 submit 方法
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Future future = executorService.submit(futureDemo);

		System.out.println(future.get()); // 阻塞获取结果
		executorService.shutdown();
	}
}
