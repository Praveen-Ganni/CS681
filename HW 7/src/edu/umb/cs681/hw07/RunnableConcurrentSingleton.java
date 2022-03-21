package edu.umb.cs681.hw07;
public class RunnableConcurrentSingleton implements Runnable {
	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());
	}
	public static void main(String[] args) {
		Thread thread1 = new Thread(new RunnableConcurrentSingleton());
		Thread thread2 = new Thread(new RunnableConcurrentSingleton());
		Thread thread3 = new Thread(new RunnableConcurrentSingleton());
		thread1.start();
		thread2.start();
		thread3.start();
	}
}