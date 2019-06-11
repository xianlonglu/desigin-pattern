package com.lxl.thread.lock.twinsLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的 访问将被阻塞，我们将这个同步工具命名为TwinsLock。
 * 
 * 首先，确定访问模式。同一时刻支持多个线程的访问，这显然是共享式 访问，因此，需要使用同步器提供的 acquireShared(int
 * args)方法等和Shared相关的方法，这就要求TwinsLock必须重写 tryAcquireShared(int
 * args)方法和tryReleaseShared(int args)方法，这样才能 保证同步器的共享式同步状态的获取与释放方法得以执行。
 * 
 * 其次，定义资源数。T同一时刻允许至多两个线程的同时访问，表明同步资源数为2，这样可以设置初始状态status为2，当一个线程进行获取， status减1
 * ，该线程释放，则status加1，状态的合法范围为0、1和2，其中0表示当前已经有两个线程获取了同步资源，此时再有其他线程对同步状态进行获取，
 * 该线程只能被阻塞。在同步状态变更时，需要使用 compareAndSet(int expect,int update)方法做原子性保障。
 * 
 * 最后，组合自定义同步器。前面的章节提到，自定义同步组件通过组合自定义同步器来完成同步功能，一般情况下自定义同步器会被定义为自定义同步组件的内部类。
 * 
 * @author Administrator
 *
 */
public class TwinsLock implements Lock {
	private final Sync sync = new Sync(2);

	private static final class Sync extends AbstractQueuedSynchronizer {
		Sync(int count) {
			if (count <= 0) {
				throw new IllegalArgumentException("count must large than zero.");
			}
			setState(count);
		}

		public int tryAcquireShared(int reduceCount) {
			for (;;) {
				int current = getState();
				int newCount = current - reduceCount;
				if (newCount < 0 || compareAndSetState(current, newCount)) {
					return newCount;
				}
			}
		}

		public boolean tryReleaseShared(int returnCount) {
			for (;;) {
				int current = getState();
				int newCount = current + returnCount;
				if (compareAndSetState(current, newCount)) {
					return true;
				}
			}
		}
	}

	public void lock() {
		sync.acquireShared(1);
	}

	public void unlock() {
		sync.releaseShared(1);
	}

	// 其他接口方法略
	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquireShared(1) >= 0;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
}