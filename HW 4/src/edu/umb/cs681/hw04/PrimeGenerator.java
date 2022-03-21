package edu.umb.cs681.hw04;
import java.util.LinkedList;

public class PrimeGenerator implements Runnable {

    protected LinkedList<Long> primes = new LinkedList<Long>();
    protected long from, to;

    public PrimeGenerator(long from, long to){
        if(from >= 1 && to > from){
            this.from = from;
            this.to = to;
        }else{
            throw new RuntimeException("Wrong input values: from=" + from + " to=" + to);
        }
    }

    protected boolean isEven(long n){
        if(n%2 == 0){ return true; }
        else{ return false; }
    }

    protected boolean isPrime(long n){
        // 1 or lower numbers are not prime.
        if(n <= 1){ return false; }
        // Even numbers are not prime, except for 2.
        if( n > 2 && isEven(n) ){ return false; }
        long i;
        // Find a number "i" that can divide "n"
        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
        // If such a number "i" is found, n is not prime. Otherwise, n is prime.
        if (i == 1){ return true; }
        else{ return false; }
    }

    @Override
    public void run() {
        for( long i = from; i <= to; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        System.out.println(primes);
    }
}
