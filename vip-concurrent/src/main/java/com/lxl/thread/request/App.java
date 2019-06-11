package com.lxl.thread.request;

/**
 * 如何把多线程用得更加优雅 合理的利用异步操作，可以大大提升程序的处理性能，下面这个案例，如果看 过 zookeeper 源码的同学应该都见过
 * 通过阻塞队列以及多线程的方式，实现对请求的异步化处理，提升处理性能
 * 
 * @author Administrator
 *
 */
public class App {

	static IRequestProcessor requestProcessor;

	public void setUp() {
		PrintProcessor printProcessor = new PrintProcessor();
		printProcessor.start();
		SaveProcessor saveProcessor = new SaveProcessor(printProcessor);
		saveProcessor.start();

		requestProcessor = new PrevProcessor(saveProcessor);
		((PrevProcessor) requestProcessor).start();
	}

	public static void main(String[] args) {
		App app = new App();
		app.setUp();
		Request request = new Request();
		request.setName("Mic");
		requestProcessor.process(request);
	}

}
