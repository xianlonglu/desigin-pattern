package com.lxl.thread.threadpool;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的监控
 * 
 * 如果在系统中大量使用线程池，则有必要对线程池进行监控，方便在出现问题时，可以根
 * 据线程池的使用状况快速定位问题。可以通过线程池提供的参数进行监控，在监控线程池的 时候可以使用以下属性。
 * 
 * @author Administrator
 *
 */
public class ThreadPoolDemo extends ThreadPoolExecutor {

	// 保存任务开始执行的时间,当任务结束时,用任务结束时间减去开始时间计算任务执行时间
	private ConcurrentHashMap<String, Date> startTimes;

	public static ExecutorService newCachedThreadPool() {
		return new ThreadPoolDemo(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue());
	}

	public ThreadPoolDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.startTimes = new ConcurrentHashMap<>();
	}

	public ThreadPoolDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
	}

	public ThreadPoolDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}

	public ThreadPoolDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}

	/**
	 * 任务执行前
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		this.getPoolSize(); // 线程池的线程数量。如果线程池不销毁的话，线程池里的线程不会自动销毁，所以这个大小只增不减。
		this.getActiveCount(); // 获取活动的线程数。
		this.getTaskCount(); // 任务总量taskCount
		this.getCompletedTaskCount();// 线程池在运行过程中已完成的任务数量，小于或等于taskCount。
		// 线程池里曾经创建过的最大线程数量。通过这个数据可以知道线程池是否曾经满过。如该数值等于线程池的最大大小，则表示线程池曾经满过。
		this.getLargestPoolSize();
		startTimes.put(String.valueOf(r.hashCode()), new Date());
	}

	/**
	 * 线程池关闭前
	 */
	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return super.isTerminated();
	}

	/**
	 * 任务执行后
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
		Date finishDate = new Date();
		long diff = finishDate.getTime() - startDate.getTime();
		// 统计任务耗时、初始线程数、核心线程数、正在执行的任务数量、
		// 已完成任务数量、任务总数、队列里缓存的任务数量、
		// 池中存在的最大线程数、最大允许的线程数、线程空闲时间、线程池是否关闭、线程池是否终止
		System.out.print("任务耗时:" + diff + "\n");
		System.out.print("初始线程数:" + this.getPoolSize() + "\n");
		System.out.print("核心线程数:" + this.getCorePoolSize() + "\n");
		System.out.print("正在执行的任务数量:" + this.getActiveCount() + "\n");
		System.out.print("已经执行的任务数:" + this.getCompletedTaskCount() + "\n");
		System.out.print("任务总数:" + this.getTaskCount() + "\n");
		System.out.print("最大允许的线程数:" + this.getMaximumPoolSize() + "\n");
		System.out.print("线程空闲时间:" + this.getKeepAliveTime(TimeUnit.MILLISECONDS) + "\n");
		System.out.println();
		super.afterExecute(r, t);
	}

	@Override
	public void shutdown() {
		System.err.println("已经执行的任务数：" + this.getCompletedTaskCount() + "," + "当前活动线程数:" + this.getActiveCount()
				+ ",当前排队线程数:" + this.getQueue().size());
		System.out.println();
		super.shutdown();
	}

	private static ExecutorService es = ThreadPoolDemo.newCachedThreadPool();

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			es.execute(new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}));
		}
		es.shutdown();
	}
}
