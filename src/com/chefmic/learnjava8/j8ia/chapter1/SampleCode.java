package com.chefmic.learnjava8.j8ia.chapter1;

import com.chefmic.learnjava8.j8ia.object.Apple;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Locale.filter;

/**
 * Created by chenyuan on 12/4/16.
 */
public class SampleCode {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        filterApples(inventory, (Apple a) -> "green".equals(a.getColor()) || a.getWeight() > 180);
    }

    //1.2.1
    public File[] getHiddenFiles() {
        //Tradition ways
        File[] files = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        //Or we can do:
        files = new File(".").listFiles(File::isHidden);
        return files;
    }

    //1.2.2
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}
