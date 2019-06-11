package com.lxl.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * FixedThreadPool线程池实例
 * 
 * @author Administrator
 *
 */
public class NewFixedThreadPoolDemo implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
	}

	static ExecutorService service = Executors.newFixedThreadPool(3);

	public static void main(String[] args) {
		/**
		 * 默认情况下，创建线程池之后，线程池中是没有线程的，需要提交任务之后才会创建线程。在实际中如果需要线程池创建之
		 * 后立即创建线程，可以通过以下两个方法办到： prestartCoreThread()：初始化一个核心线程；
		 * prestartAllCoreThreads()：初始化所有核心线程
		 */
		// ThreadPoolExecutor tpe = (ThreadPoolExecutor) service;
		// tpe.prestartCoreThread();
		// tpe.prestartAllCoreThreads();

		for (int i = 0; i < 100; i++) {
			// service.submit(new NewFixedThreadPoolDemo());
			service.execute(new NewFixedThreadPoolDemo());
			/**
			 * submit和execute的区别 执行一个任务，可以使用submit和execute，这两者有什么区别呢？
			 * \1.execute只能接受Runnable类型的任务
			 * \2.submit不管是Runnable还是Callable类型的任务都可以接受，但是Runnable返回值均为void，所以使用
			 * Future的get()获得的还是null
			 */
			/**
			 * submit()方法用于提交需要返回值的任务。线程池会返回一个future类型的对象，
			 * 通过这个future对象可以判断任务是否执行成功
			 * ，并且可以通过future的get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成，而使用get（long
			 * timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。
			 */
		}
		service.shutdown();// 优化的关闭
		service.shutdownNow();// 立马关闭

		/**
		 * 原理:遍历线程池中的工作线程，然后逐个调用线程的interrupt方法来中断线程，所以无法响应中断的任务可能永远无法终止。
		 * shutdownNow首先将线程池的状态设置成STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表。
		 * shutdown只是将线程池的状态设置成SHUTDOWN状态，然后中断所有没有正在执行任务的线 程。
		 * 只要调用了这两个关闭方法中的任意一个， isShutdown方法就会返回true。
		 * 当所有的任务都已关闭后，才表示线程池关闭成功，这时调用isTerminaed方法会返回true。 至于应该调用哪
		 * 一种方法来关闭线程池，应该由提交到线程池的任务特性决定， 通常调用shutdown方法来关闭
		 * 线程池，如果任务不一定要执行完，则可以调用shutdownNow方法。
		 */

	}
}