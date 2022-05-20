package edu.umb.cs681.hw018;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	final ConcurrentMap<Path, AtomicLong> counterMap = new ConcurrentHashMap<>();
	private ReentrantLock rl = new ReentrantLock();  

	public void increment(Path path){
		rl.lock();
		try{
			counterMap.computeIfAbsent(path, p -> new AtomicLong()).incrementAndGet();
		} finally {
			rl.unlock();
		}
	}

	public long getCount(Path path){
		rl.lock();
		try{
			AtomicLong al = counterMap.get(path);
		    return al == null ? 0 : al.get();
		} finally {
			rl.unlock();
		}
	}
	
	private static AccessCounter ac = null;
	private static ReentrantLock rl1 = new ReentrantLock();
	// Factory method to create or return the singleton instance
	public static AccessCounter getInstance(){
		rl1.lock();
		try{
			if(ac==null){ ac = new AccessCounter(); }
			return ac;
		}finally{
			rl1.unlock();
		}
	}



}

