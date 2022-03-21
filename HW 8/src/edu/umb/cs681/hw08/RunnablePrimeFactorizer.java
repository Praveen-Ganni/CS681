package edu.umb.cs681.hw08;
// Generates prime factors of a given number (d)
// Factorization is performed in the range of 2 and Math.sqrt(d)
//
// When only one thread is used for factorization, create a Runnable as follows:
// new RunnablePrimeFactorizer(d, 2, (long)Math.sqrt(d));
// The thread performs factorization for the entire range of 2 and sqrt(d).
//
// When two threads are used for factorization, create two Runnables as follows:
// new RunnablePrimeFactorizer(d, 2, (long)Math.sqrt(d)/2);
// new RunnablePrimeFactorizer(d, 1+(long)Math.sqrt(d)/2,(long)Math.sqrt(d));
public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {
	public RunnablePrimeFactorizer(long d, long from, long to) {
		super(d);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException("from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
		}
	}
	protected boolean isEven(long n){
		if(n%2 == 0){ 
			return true; 
		}
		else{ 
			return false; 
		}
	}
	public void generatePrimeFactors() {
		long x = from;
		while( d != 1 && x <= to ){
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
	}
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}
}