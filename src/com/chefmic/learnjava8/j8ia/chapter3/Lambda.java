package com.chefmic.learnjava8.j8ia.chapter3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by chenyuan on 12/4/16.
 */
public class Lambda {

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("a", "b", "c", "d");
        strs.sort((String s1, String s2) -> s1.compareTo(s2));
        strs.sort(String::compareToIgnoreCase);
        strs.sort(Comparator.comparing(String::toString).reversed());

        Function<String, Integer> toIntFunction = (String s) -> Integer.parseInt(s);
        toIntFunction = Integer::parseInt;
        toIntFunction.apply("1234");

        BiPredicate<List<String>, String> contains = (List<String> list, String s) -> list.contains(s);
        contains = List::contains;

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> 2 * x;
        Function<Integer, Integer> compose = f.compose(g);
        compose = f.andThen(g);

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline =
                addHeader.andThen(Letter::addFooter).andThen(Letter::checkSpelling);
        System.out.println(transformationPipeline.apply("Hello World"));
    }

}
