package com.lxl.singleton.hungry;

//饿汉式静态块单例
public class HungryStaticSingleton {
	private static final HungryStaticSingleton singleton;

	static {
		singleton = new HungryStaticSingleton();
	}

	private HungryStaticSingleton() {

	}

	public static HungryStaticSingleton getInstance() {
		return singleton;
	}
}
