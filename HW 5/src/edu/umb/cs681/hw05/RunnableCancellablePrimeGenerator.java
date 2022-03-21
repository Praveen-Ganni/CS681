package edu.umb.cs681.hw05;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = false;
		} finally {
			lock.unlock();
		}
	}
	public void generatePrimes() {
		for (long n = from; n <= to; n++) {
			lock.lock();
			try {
				if (done) {
					break;
				}
				if (isPrime(n)) {
					this.primes.add(n);
				}
			} finally {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator generator = new
		RunnableCancellablePrimeGenerator(1,100);
		Thread thread = new Thread(generator);
		thread.start();
		generator.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		generator.getPrimeNumbers().forEach( (Long prime)-> System.out.println(prime + ", ") );
		System.out.println("\n" + generator.getPrimeNumbers().size() + " prime numbers are found.");
	}
}