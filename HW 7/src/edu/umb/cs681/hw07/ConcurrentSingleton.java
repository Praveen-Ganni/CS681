package edu.umb.cs681.hw07;
import java.util.concurrent.locks.ReentrantLock;
public class ConcurrentSingleton {
	private static ConcurrentSingleton i = null;
	private static ReentrantLock lock = new ReentrantLock();
	public static ConcurrentSingleton getInstance(){
		lock.lock();
		try {
			if (i == null)
			i = new ConcurrentSingleton();
			return i;
		} finally {
			lock.unlock();
		}
	}
}