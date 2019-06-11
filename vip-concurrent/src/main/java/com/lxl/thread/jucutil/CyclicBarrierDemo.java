package com.lxl.thread.jucutil;

import java.util.concurrent.CyclicBarrier;

/**
 * 同步屏障CyclicBarrier， 场景：当存在需要所有的子任务都完成时，才执行主任务
 * 
 * 
 * @author Administrator
 *
 */
public class CyclicBarrierDemo extends Thread {
	@Override
	public void run() {
		System.out.println("开始进行数据分析");
	}

	// 循环屏障
	// 可以使得一组线程达到一个同步点之前阻塞.

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrierDemo());
		new Thread(new DataImportThread(cyclicBarrier, "file1")).start();
		new Thread(new DataImportThread(cyclicBarrier, "file2")).start();
		new Thread(new DataImportThread(cyclicBarrier, "file3")).start();

	}
	
	

}
