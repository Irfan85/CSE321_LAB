package LabAssignment03;

import java.util.Arrays;
import java.util.TreeSet;

public class Task02 {
    public static void main(String[] args) {
        long startTime, endTime;

        DivisorCounter thread0 = new DivisorCounter(1, 10000);
        DivisorCounter thread1 = new DivisorCounter(10001, 20000);
        DivisorCounter thread2 = new DivisorCounter(20001, 30000);
        DivisorCounter thread3 = new DivisorCounter(30001, 40000);
        DivisorCounter thread4 = new DivisorCounter(40001, 50000);
        DivisorCounter thread5 = new DivisorCounter(50001, 60000);
        DivisorCounter thread6 = new DivisorCounter(60001, 70000);
        DivisorCounter thread7 = new DivisorCounter(70001, 80000);
        DivisorCounter thread8 = new DivisorCounter(80001, 90000);
        DivisorCounter thread9 = new DivisorCounter(90001, 100000);

        startTime = System.currentTimeMillis();

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();

        try {
            thread0.join();
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
            thread9.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        endTime = System.currentTimeMillis();

        System.out.println("Multithreaded execution time: " + (endTime - startTime) + "ms");

        int[] results = {thread0.getResult(), thread1.getResult(), thread2.getResult(), thread3.getResult(), thread4.getResult(), thread5.getResult(), thread6.getResult(), thread7.getResult(), thread8.getResult(), thread9.getResult()};

        Arrays.sort(results);
        System.out.println("Multithreaded result: " + results[9]);

        startTime = System.currentTimeMillis();

        thread0.run();
        thread1.run();
        thread2.run();
        thread3.run();
        thread4.run();
        thread5.run();
        thread6.run();
        thread7.run();
        thread8.run();
        thread9.run();

        endTime = System.currentTimeMillis();

        System.out.println("Single-Threaded execution time: " + (endTime - startTime) + "ms");

        results = new int[]{thread0.getResult(), thread1.getResult(), thread2.getResult(), thread3.getResult(), thread4.getResult(), thread5.getResult(), thread6.getResult(), thread7.getResult(), thread8.getResult(), thread9.getResult()};

        Arrays.sort(results);
        System.out.println("Single-Threaded result:" + results[9]);
    }
}

class DivisorCounter extends Thread {
    private int start, end;
    private int result = 0;

    public DivisorCounter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            TreeSet<Integer> divisors = new TreeSet<>();

            for (int j = 1; j <= (int) Math.sqrt(i); j++) {
                if ((i % j) == 0) {
                    divisors.add(j);
                    divisors.add(i / j);
                }
            }

            if (divisors.size() > result) {
                result = divisors.size();
            }
        }
    }
}