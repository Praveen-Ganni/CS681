package edu.umb.cs681.hw09;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton{
	private static AtomicReference<ConcurrentSingleton> atomic_instance = new AtomicReference<>();
	public static ConcurrentSingleton getInstance(){
		
		ConcurrentSingleton obj = atomic_instance.get();
		
		if(obj == null) {
			obj = new ConcurrentSingleton();
			
			if(!atomic_instance.compareAndSet(null, obj)) {
				obj = atomic_instance.get();
			}
		}
		return obj;
	} 
	
	public static void main(String[] args){
		for(int n=0; n<10; n++){
		new Thread(
				()->{System.out.println(ConcurrentSingleton.getInstance());}).start();
		}
	}
}