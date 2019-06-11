package com.lxl.thread;

import java.util.concurrent.TimeUnit;

/**
 * ThreadInterrupt实例
 * 
 * @author Administrator
 *
 */
public class ThreadInterruptDemo {

	// public static void main(String[] args) throws InterruptedException {
	// Thread thred=new Thread(()->{
	// while(true){
	// // 默认false
	// boolean in=Thread.currentThread().isInterrupted();
	// if(in){
	// System.out.println("before:"+in);
	// Thread.interrupted();//设置复位
	// System.out.println("after:"+Thread.currentThread().isInterrupted());
	// }
	// }
	// });
	// thred.start();
	// TimeUnit.SECONDS.sleep(1);
	// thred.interrupt(); //终端
	// }

//    public static void main(String[] args) throws InterruptedException {
//        Thread thread=new Thread(()->{
//            while(true){
//				try {
//					// 中断一个处于阻塞状态的线程。join/wait/queue.take..
//					TimeUnit.SECONDS.sleep(10);
//					System.out.println("demo");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//					break;
//				}
//            }
//        });
//        thread.start();
//        TimeUnit.SECONDS.sleep(1);
//        thread.interrupt();
//        System.out.println("before:"+thread.isInterrupted());
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println("after:"+thread.isInterrupted());
//    }
//   volatile boolean stop=true;
    
	private volatile static boolean stop = false;

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			int i = 0;
			while (!stop) {
				i++;
			}
		});
		thread.start();
		System.out.println("begin start thread");
        //TimeUnit.SECONDS.sleep(1);
        //TimeUnit.MILLISECONDS.sleep(10);
		Thread.sleep(1000);
		stop = true;
	}

}
