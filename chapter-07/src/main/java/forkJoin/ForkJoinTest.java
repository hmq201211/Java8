package forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ForkJoinTest {
    private static long[] longArrayGenerator(int n) {
        return LongStream.rangeClosed(1, n).toArray();
    }

    public static void main(String[] args) {
        long[] numbers = longArrayGenerator(10_000_000);
        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(numbers);
        System.out.println("new ForkJoinPool().invoke(forkJoinSumCalculator) = " + new ForkJoinPool().invoke(forkJoinSumCalculator));
    }
}
