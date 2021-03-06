package test;

import pojo.Trader;
import pojo.Transaction;

import java.io.IOException;
import java.lang.annotation.Target;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Test {
    public static void main(String[] args) {
        fibonacci().limit(10).forEach(
                integers -> System.out.println(integers[0] + ":" + integers[1])
        );
        fibonacci(new IntSupplier() {
            Integer previous = 0;
            Integer current = 1;
            @Override
            public int getAsInt() {
                Integer temp = previous;
                previous = current;
                current = previous + temp;
                return temp;
            }
        }).limit(5).forEach(System.out::println);
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)

        );
        question1(transactions);
        question2(transactions);
        question3(transactions);
        question4(transactions);
        question5(transactions);
        question6(transactions);
        question7(transactions);
        question8(transactions);

        List<int[]> collect = IntStream.rangeClosed(1, 100).boxed().flatMap(
                i -> IntStream.rangeClosed(i, 100).filter(j -> Math.sqrt(i * i + j * j) % 1 == 0).mapToObj(j -> new int[]{i, j, (int) Math.sqrt(i * i + j * j)})
        ).collect(Collectors.toList());
        collect.forEach(i -> System.out.println(i[0] + ":" + i[1] + ":" + i[2]));
        List<String> list = mostFrequentWords("chapter-05/src/main/resources/Romeo and Juliet.txt");
        System.out.println(list);
    }

    //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
    private static void question1(List<Transaction> transactions) {
        List<Transaction> collect = transactions.stream().filter(i -> i.getYear() == 2011).sorted(Comparator.comparingInt(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(collect);
    }

    //(2) 交易员都在哪些不同的城市工作过？
    private static void question2(List<Transaction> transactions) {
        Set<String> collect = transactions.stream().map(i -> i.getTrader().getCity()).collect(Collectors.toSet());
        System.out.println(collect);
    }

    //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
    private static void question3(List<Transaction> transactions) {
        List<Trader> collect = transactions.stream().map(Transaction::getTrader).distinct().filter(i -> "Cambridge".equals(i.getCity())).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(collect);
    }

    //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
    private static void question4(List<Transaction> transactions) {
        String collect = transactions.stream().map(Transaction::getTrader).distinct().map(Trader::getName).sorted().reduce("", (n1, n2) -> n1 + n2);
        System.out.println(collect);
    }

    //(5) 有没有交易员是在米兰工作的？
    private static void question5(List<Transaction> transactions) {
        boolean milan = transactions.stream().map(Transaction::getTrader).anyMatch(i -> i.getCity().equals("Milan"));
        System.out.println(milan);
    }

    //(6) 打印生活在剑桥的交易员的所有交易额。
    private static void question6(List<Transaction> transactions) {
        List<Integer> collect = transactions.stream().filter(i -> "Cambridge".equals(i.getTrader().getCity())).map(Transaction::getValue).collect(Collectors.toList());
        System.out.println(collect);
    }

    //(7) 所有交易中，最高的交易额是多少？
    private static void question7(List<Transaction> transactions) {
        Integer integer = transactions.stream().map(Transaction::getValue).max(Integer::compare).orElse(0);
        System.out.println(integer);
    }

    //(8) 找到交易额最小的交易。
    private static void question8(List<Transaction> transactions) {
        Optional<Transaction> integer = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        System.out.println(integer.get());
    }

    private static List<String> mostFrequentWords(String path) {
        try (Stream<String> line = Files.lines(Paths.get(path), Charset.defaultCharset())) {
            Map<String, Long> collect = line.flatMap(i -> Arrays.stream(i.split(" "))).collect(groupingBy(i -> i, counting()));
            return collect.entrySet().stream().sorted((i, j) -> (int) (j.getValue() - i.getValue())).limit(50).map(Map.Entry::getKey).collect(toList());
        } catch (IOException exception) {
            return null;
        }
    }

    private static Stream<Integer[]> fibonacci() {
        return Stream.iterate(new Integer[]{0, 1}, i -> new Integer[]{i[1], i[0] + i[1]});
    }

    private static IntStream fibonacci(IntSupplier intSupplier){
                return IntStream.generate(intSupplier);
    }
}
