package com.chefmic.learnjava8.j8ia.chapter2;

import com.chefmic.learnjava8.j8ia.Apple;

import java.util.List;

/**
 * Created by chenyuan on 12/4/16.
 */
public class SampleCode {

    //Test 2.1
    public static void printPrettyApples(List<Apple> inventory, AppleFormatter<Apple> predicate) {
        AppleFormatter<Apple> formatter = (Apple apple) -> apple.getColor();
        Runnable runner = () -> System.out.println("Hello");
        for (Apple apple : inventory) {
        }

    }



}
