package test;

import pojo.Trader;
import pojo.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Test {
    public static void main(String[] args) {
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
    }

    //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
    private static void question1(List<Transaction> transactions) {
        List<Transaction> collect = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(collect);
    }

    //(2) 交易员都在哪些不同的城市工作过？
    private static void question2(List<Transaction> transactions) {
        List<String> collect = transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
    private static void question3(List<Transaction> transactions) {
        List<Trader> collect = transactions.stream().map(Transaction::getTrader).distinct().filter(trader -> trader.getCity().equals("Cambridge")).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        System.out.println(collect);
    }

    //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
    private static void question4(List<Transaction> transactions) {
        String collect = transactions.stream().map(i -> i.getTrader().getName()).distinct().sorted(String::compareTo).collect(joining());
        System.out.println(collect);
    }

    //(5) 有没有交易员是在米兰工作的？
    private static void question5(List<Transaction> transactions) {
        long milan = transactions.stream().map(Transaction::getTrader).filter(i -> i.getCity().equals("Milan")).count();
        if (milan > 0)
            System.out.println(true);
    }

    //(6) 打印生活在剑桥的交易员的所有交易额。
    private static void question6(List<Transaction> transactions) {
        List<Integer> collect = transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).collect(Collectors.toList());
        System.out.println(collect);
    }

    //(7) 所有交易中，最高的交易额是多少？
    private static void question7(List<Transaction> transactions) {
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).max(Integer::compareTo);
        max.ifPresent(System.out::println);
    }

    //(8) 找到交易额最小的交易。
    private static void question8(List<Transaction> transactions) {
        Optional<Transaction> min = transactions.stream().min(Comparator.comparingInt(Transaction::getValue));
        min.ifPresent(System.out::println);
    }

}
