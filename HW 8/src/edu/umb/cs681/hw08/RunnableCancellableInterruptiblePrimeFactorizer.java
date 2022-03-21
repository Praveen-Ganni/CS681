package edu.umb.cs681.hw08;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellableInterruptiblePrimeFactorizer extends
RunnableCancellablePrimeFactorizer {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellableInterruptiblePrimeFactorizer(long d, long from, long to) {
		super(d, from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = true;
		} 
		finally {
			lock.unlock();
		}
	}
	public void generatePrimeFactors() {
		long x = from;
		while (d != 1 && x <= to) {
			lock.lock();
			try {
				if (done) {
					System.out.println("Factors generation stopped");
					this.factors.clear();
					break;
				}
				if (x > 2 && isEven(x)) {
					x++;
					continue;
				}
				if (d % x == 0) {
					factors.add(x);
					d /= x;
				} else {
					if (x == 2) {
						x++;
					} else {
						x += 2;
					}
				}
			} 
			finally {
				lock.unlock();
			}
			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				continue;
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellableInterruptiblePrimeFactorizer gen = new
		RunnableCancellableInterruptiblePrimeFactorizer(9,2, 100);
		Thread thread = new Thread(gen);
		thread.start();
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.setDone();
		thread.interrupt();
		try {
			thread.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n" + "Generated Prime Factors for the given dividends are: " + gen.getPrimeFactors());
	}
}