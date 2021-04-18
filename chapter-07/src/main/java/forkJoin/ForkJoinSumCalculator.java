package forkJoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_100;

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }


    @Override
    protected Long compute() {
        final int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftRecursiveTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftRecursiveTask.fork();
        ForkJoinSumCalculator rightRecursiveTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        long right = rightRecursiveTask.compute();
        long left = leftRecursiveTask.join();
        return left + right;
    }

    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
