package com.chefmic.learnjava8.j8ia.chapter3;

import com.chefmic.learnjava8.j8ia.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by chenyuan on 12/4/16.
 */
public class ProcessFile {

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        Predicate<String> p = s -> list.add(s);

        Runnable o = () -> { System.out.println(); };

        Comparator<Apple> comparator = (a1, a2) -> Integer.compare(a1.getWeight(), a2.getWeight());
    }

    public static String processFile() throws IOException {
        try (BufferedReader br
                     = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br
                     = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }
}
