package edu.umb.cs681.hw04;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class WithOneThread {

    public static void main(String[] args) {
        Runnable task = new PrimeGenerator(1, 2000000);
        Thread t = new Thread(task);
        t.start();

        Long startTime = System.currentTimeMillis();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();

        System.out.println("Time taken using 1 thread = "+ Math.abs(endTime - startTime) + " millisecond");

    }
}