package test;

import formatter.AppleFormat;
import formatterImpl.PrintColor;
import pojo.Apple;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private static final List<Apple> APPLE_LIST;

    static {
        APPLE_LIST = new ArrayList<>();
        APPLE_LIST.add(new Apple("red", 1));
        APPLE_LIST.add(new Apple("black", 2));
    }


    public static void main(String[] args) {
        System.out.println(APPLE_LIST.size());
        printApples(new PrintColor());
    }

    private static void printApples(AppleFormat appleFormat) {
        for (Apple apple : Test.APPLE_LIST) {
            String format = appleFormat.accept(apple);
            System.out.println(format);
        }
    }
}
