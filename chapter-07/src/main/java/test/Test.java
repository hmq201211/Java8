package test;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        System.out.println(measureSumPerf(sequentialSum(), 10000000));
        System.out.println(measureSumPerf(iterativeSum(), 10000000));
        System.out.println(measureSumPerf(parallelSum(), 10000000));
        System.out.println("---------");
        System.out.println(measureSumPerf(rangedParallelSum(),10000000));
        System.out.println(measureSumPerf(rangedSequentialSum(),10000000));
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public static Function<Long, Long> sequentialSum() {
        return (n) -> Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static Function<Long, Long> parallelSum() {
        return (n) -> Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static Function<Long, Long> iterativeSum() {
        return (n) -> {
            long result = 0;
            for (long i = 1L; i <= n; i++) {
                result += i;
            }
            return result;
        };
    }

    public static Function<Long, Long> rangedSequentialSum() {
        return (n) -> LongStream.rangeClosed(1, n).reduce(0, Long::sum);
    }

    public static Function<Long, Long> rangedParallelSum() {
        return (n) -> LongStream.rangeClosed(1, n).parallel().reduce(0, Long::sum);
    }
}
