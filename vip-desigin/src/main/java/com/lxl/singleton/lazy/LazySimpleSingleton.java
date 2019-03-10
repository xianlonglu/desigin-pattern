package com.lxl.singleton.lazy;

//懒汉式单例
//在外部需要使用的时候才进行实例化
public class LazySimpleSingleton {
	private static LazySimpleSingleton singleton = null;
	private LazySimpleSingleton() {	}

	public synchronized static LazySimpleSingleton getInstance() {
		if (singleton == null) {
			singleton = new LazySimpleSingleton();
		}
		return singleton;
	}
}
