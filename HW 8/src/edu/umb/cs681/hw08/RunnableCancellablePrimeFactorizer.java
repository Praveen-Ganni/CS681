package edu.umb.cs681.hw08;
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
				if( x > 2 && isEven(x)) {
					x++;
					continue;
				}
				if(d % x == 0) {
					factors.add(x);
					d /= x;
				}else {
					if(x==2){ x++; }
					else{ x += 2; }
				}
			} 
			finally {
				lock.unlock();
			}
		}
	}
}