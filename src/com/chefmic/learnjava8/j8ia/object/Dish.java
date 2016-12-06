package com.chefmic.learnjava8.j8ia.object;

/**
 * Created by chenyuan on 12/4/16.
 */
public class Dish {

    public enum CaloricLevel { Low, Normal, High }

    private final String name;
    private final int calories;
    private final Type type;
    private final boolean vegetarian;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        Meat, Fish, Other
    }
}
