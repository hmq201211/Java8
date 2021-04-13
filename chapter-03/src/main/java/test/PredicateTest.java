package test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class PredicateTest {

    private static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> toReturn = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                toReturn.add(t);
            }
        }
        return toReturn;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "hi", "");
        List<String> nonEmpty = filter(list,(String s)-> !s.isEmpty());
        System.out.println("nonEmpty = " + nonEmpty);
    }
}
