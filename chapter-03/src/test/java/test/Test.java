package test;

import pojo.Dish;

import java.util.*;

public class Test {
    private static List<Dish> dishes;

    static {
        dishes = Arrays.asList(new Dish(1, "noddle"),
                new Dish(2, "apple"),
                new Dish(3, "banana"));
    }

    public static void main(String[] args) {


        List<Dish> dishesGreaterThan1 = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getWeight() > 1) {
                dishesGreaterThan1.add(dish);
            }
        }
        Collections.sort(dishesGreaterThan1, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        List<String> names = new ArrayList<>();
        dishesGreaterThan1.forEach(dish -> {
            names.add(dish.getName());
        });
        System.out.println(names);
    }
}
