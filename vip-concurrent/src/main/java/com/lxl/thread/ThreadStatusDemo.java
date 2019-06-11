package com.lxl.thread;

import java.util.concurrent.TimeUnit;

/*启动一个线程前，最好为这个线程设置线程名称，因为这样在使用 jstack 分析程序或者进行问题排查时，就会给开发人员提供一些提示
 显示线程的状态
 ➢ 运行该示例，打开终端或者命令提示符，键入“jps”，（JDK1.5 提供的一个显示当前所有 java 进程 pid 的命令）
 ➢ 根据上一步骤获得的 pid，继续输入 jstack pid（jstack是 java 虚拟机自带的一种堆栈跟踪工具。jstack 用于
 打印出给定的 java 进程 ID 或 core file 或远程调试服务的 Java 堆栈信息）
 通过上面的分析，我们了解到了线程的生命周期，现在在整个生命周期中并不是固定的处于某个状态，而是随着代码的执行在不同的状态之间进行切换*/
public class ThreadStatusDemo {

	public static void main(String[] args) {
		new Thread(() -> {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "timewaiting").start();

		new Thread(() -> {
			while (true) {
				synchronized (ThreadStatusDemo.class) {
					try {
						ThreadStatusDemo.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "waiting").start();

		new Thread(new BlockDemo(), "BlockDemo-0").start();
		new Thread(new BlockDemo(), "BlockDemo-1").start();

	}

	static class BlockDemo extends Thread {
		public void run() {
			synchronized (BlockDemo.class) {
				while (true) {
					try {
						TimeUnit.SECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
