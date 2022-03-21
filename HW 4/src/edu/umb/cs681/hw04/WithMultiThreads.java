package edu.umb.cs681.hw04;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class WithMultiThreads {

    public static void main(String[] args) {
        Runnable task1 = new PrimeGenerator(1L, 2000000L);
        Runnable task2 = new PrimeGenerator(1L, 2000000L);
        Runnable task3 = new PrimeGenerator(1L, 2000000L);
        Runnable task4 = new PrimeGenerator(1L, 2000000L);

        // Creating threads
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);
        Thread t4 = new Thread(task4);

        // starting threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Long startTime = System.currentTimeMillis();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long endTime = System.currentTimeMillis();

        System.out.println("Time taken using multiple threads = "+ Math.abs(endTime - startTime) + " millisecond");
    }
}
