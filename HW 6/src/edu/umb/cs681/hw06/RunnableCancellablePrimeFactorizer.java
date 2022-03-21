package edu.umb.cs681.hw06;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellablePrimeFactorizer (long d, long from, long to){
		super(d, from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = false;
		}
		finally {
			lock.unlock();
		}
	}
	public void generatePrimeFactors(){
		long x = from;
		while( d != 1 && x <= to ){
			lock.lock();
			try{
				if(done){
					break;
				}
				if( d > 2 && isEven(x)) {
					x++;
					continue;
				}
				if(d % x == 0) {
					factors.add(x);
					d /= x;
				}else {
					if(x==2){ 
						x++; 
					}
					else{
						x += 2;
					}
				}
			} finally {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellablePrimeFactorizer gen = new
		RunnableCancellablePrimeFactorizer(9683, 2,(long) Math.sqrt(9683));
		Thread thread = new Thread(gen);
		thread.start();
		gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Prime factors for the given value are: " + gen.getPrimeFactors() + "\n");
	}
}