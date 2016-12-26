package com.chefmic.learnjava8.j8ia.chapter9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cyuan on 12/26/16.
 */
public class Default {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        numbers.sort(Comparator.naturalOrder());
    }

}
