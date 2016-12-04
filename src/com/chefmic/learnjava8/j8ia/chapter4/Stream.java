package com.chefmic.learnjava8.j8ia.chapter4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by chenyuan on 12/4/16.
 */
public class Stream {

    public static void main(String[] args) {
        List<Dish> dishes = new ArrayList<>();
        Map<Dish.Type, List<Dish>> map =
                dishes.stream().collect(Collectors.groupingBy(Dish::getType));
    }

    public static List<String> lowCaloriesJava7(List<Dish> dishes) {
        List<Dish> lowCalories = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }
        Collections.sort(lowCalories, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> names = new ArrayList<>();
        for (Dish dish : lowCalories) {
            names.add(dish.getName());
        }
        return names;
    }

    public static List<String> lowCaloriesJava8(List<Dish> dishes) {
        List<String> names = dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        return names;
    }

}
