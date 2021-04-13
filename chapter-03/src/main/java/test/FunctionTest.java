package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FunctionTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hi","hello");
        List<Integer> map = map(list, String::length);
        System.out.println(map);
    }

    private static  <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> toReturn = new ArrayList<>();
        for (T t : list) {
            R apply = function.apply(t);
            toReturn.add(apply);
        }
        return toReturn;
    }
}
