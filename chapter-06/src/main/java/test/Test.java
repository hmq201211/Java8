package test;

import collectors.ToListCollector;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, 100).boxed().collect(new ToListCollector());
        System.out.println(collect);
    }
}
