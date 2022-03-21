package edu.umb.cs681.hw06;
import java.util.LinkedList;
// Generates prime factors of a given number (dividend)
// Prime factors are generated in the range of 2 and dividend
// from is the lower bound of the range (2)
// to is the upper bound of the range (d
public class PrimeFactorizer {
	protected long d, from, to;
	protected LinkedList<Long> factors = new LinkedList<Long>();
	public PrimeFactorizer(long dividend){
		if(dividend >= 2){
			this.d = dividend;
			this.from = 2;
			this.to = dividend;
		}else{
			throw new RuntimeException("Input must be >= 2");
		}
	}
	public long getDividend() {
		return d; 
	}
	public long getFrom(){ 
		return from; 
	}
	public long getTo(){ 
		return to; 
	}
	public LinkedList<Long> getPrimeFactors(){ 
		return factors; 
	}
	public void generatePrimeFactors() {
		long x = 2;
		while( d != 1 && x <= to ){
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
		}
	}
}