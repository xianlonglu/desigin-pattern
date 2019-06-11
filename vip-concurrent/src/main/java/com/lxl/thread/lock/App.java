package com.lxl.thread.lock;

public class App {
	// public synchronized void demo(){ //main获得对象锁
	// System.out.println("demo");
	// demo2();
	// }
	// public void demo2(){
	// synchronized (this) { //增加重入次数就行
	// System.out.println("demo2");
	// }//减少重入次数
	// }
	//
	// public static void main(String[] args) {
	// App app=new App();
	// app.demo();
	// }
    
	public synchronized static void demo(int i) { // main获得对象锁
		System.out.println("demo  ,i=" + i);
		demo2(i);
	}

	public static void demo2(int i) {
		synchronized (App.class) { // 增加重入次数就行
			System.out.println("demo2  ,i=" + i);
		}// 减少重入次数
	}

	public static void main(String[] args) {
		App app = new App();
		new Thread(() -> app.demo(1)).start();
		new Thread(() -> app.demo(2)).start();
		new Thread(() -> app.demo(3)).start();
		new Thread(() -> app.demo(4)).start();
		new Thread(() -> app.demo(5)).start();
		new Thread(() -> app.demo(6)).start();
		new Thread(() -> app.demo(7)).start();
		new Thread(() -> app.demo(8)).start();
	}
}
