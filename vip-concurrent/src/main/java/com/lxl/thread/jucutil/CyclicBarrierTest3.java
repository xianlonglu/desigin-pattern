package com.lxl.thread.jucutil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier和CountDownLatch的区别
 * 
 * CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数器可以使用reset()方法重 置。
 * 所以CyclicBarrier能处理更为复杂的业务场景。 例如，如果计算发生错误，可以重置计数器，并让线程重新执行一次。
 * CyclicBarrier还提供其他有用的方法
 * ,比如getNumberWaiting方法可以获得Cyclic-Barrier阻塞的线程数量，isBroken()方法用来了解阻塞的线程是否被中断。
 * 
 * @author Administrator
 *
 */
public class CyclicBarrierTest3 {
	static CyclicBarrier c = new CyclicBarrier(3);

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (Exception e) {
				}
			}
		});
		System.err.println(c.getNumberWaiting());
		thread.start();
		thread.interrupt();
		try {
			c.await();
		} catch (Exception e) {
			System.out.println(c.isBroken());
		}
		System.out.println(123);
	}
}