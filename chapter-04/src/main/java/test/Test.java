package test;

import pojo.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    private static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    private static List<String> findThreeCaloriesDishNames() {
        return menu.stream().filter(dish -> dish.getCalories() > 300).map(Dish::getName).limit(3).collect(Collectors.toList());
    }

    private static List<Integer> lengthOfDishName() {
        return menu.stream().map(dish -> dish.getName().length()).collect(Collectors.toList());
    }

    private static Integer countDishNumbers() {
        return menu.stream().map(temp -> 1).reduce(Integer::sum).orElse(0);
    }

    public static void main(String[] args) {
        System.out.println(countDishNumbers());
        System.out.println(lengthOfDishName());
        Integer[] foo = {1, 2, 3};
        Integer[] bar = {3, 4};
        List<int[]> ints = flatArraysCanBeDividedBy3(foo, bar);
        for (int[] anInt : ints) {
            System.out.println(anInt[0] + ":" + anInt[1]);
        }


    }

    public static List<int[]> flatArrays(Integer[] foo, Integer[] bar) {
        List<Integer> fooList = Arrays.asList(foo);
        List<Integer> barList = Arrays.asList(bar);
        return fooList.stream().flatMap(
                fooTemp -> barList.stream().map(barTemp -> new int[]{fooTemp, barTemp})
        ).collect(Collectors.toList());
    }

    public static List<int[]> flatArraysCanBeDividedBy3(Integer[] foo, Integer[] bar) {
        List<Integer> fooList = Arrays.asList(foo);
        List<Integer> barList = Arrays.asList(bar);
        return fooList.stream().flatMap(
                fooTemp -> barList.stream().map(barTemp -> new int[]{fooTemp, barTemp})
        ).filter(temp -> (temp[0] + temp[1]) % 3 == 0).collect(Collectors.toList());
    }
}
