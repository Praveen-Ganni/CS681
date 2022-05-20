package edu.umb.cs681.hw011;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	private static Map<Path, Integer> accessCountMap = new HashMap<>();
	private ReentrantLock rl = new ReentrantLock();  

	public void increment(Path path){
		rl.lock();
		try{
			if(accessCountMap.containsKey(path))
				accessCountMap.put(path,accessCountMap.get(path)+1);
			else
				{accessCountMap.put(path,1);}
		} finally {
			rl.unlock();
		}
	}

	public int getCount(Path path){
		rl.lock();
		int count=0;
		try{
			if(accessCountMap.containsKey(path))
				count= accessCountMap.get(path);
		} finally {
			rl.unlock();
		}
		return count;
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

