package com.chefmic.learnjava8.j8ia.chapter5;

import com.chefmic.learnjava8.j8ia.object.Dish;
import com.chefmic.learnjava8.j8ia.object.Trader;
import com.chefmic.learnjava8.j8ia.object.Transaction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by chenyuan on 12/4/16.
 */
public class Streams {

    /**
     * 5.1 Filter
     */
    public static void filter() {
        List<Dish> menu = new ArrayList<>();
        List<Dish> vegetarian = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        //Distinct
        List<Integer> nums = Arrays.asList(1, 2, 1, 3, 4, 5, 2, 6);
        nums.stream().filter(i -> i % 2 == 1).distinct().forEach(System.out::println);

        List<Dish> lowCalories = menu.stream().filter(d -> d.getCalories() < 400)
                .limit(3)
                .collect(toList());
    }

    public static void filterTest() {
        List<Dish> menu = new ArrayList<>();
        List<Dish> firstTwoMeat = menu.stream()
                .filter(d -> d.getType() == Dish.Type.Meat)
                .limit(2)
                .collect(toList());
    }

    /**
     * 5.2 Map
     */
    public static void map() {
        List<Dish> menu = new ArrayList<>();
        List<String> names = menu.stream().map(Dish::getName).collect(toList());
        List<Integer> lengths = menu.stream().map(Dish::getName).map(String::length).collect(toList());

        List<String> words = Arrays.asList("Hello", "World");
        //Error!
        words.stream().map(word -> word.split("")).distinct().collect(toList());

        words.stream().map(word -> word.split("")).map(Arrays::stream).distinct().collect(toList());
    }

    public static void mapTest() {
        List<Integer> result = Arrays.asList(1, 2, 3, 4, 5).stream()
                .map(n -> n * n).collect(toList());

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(1, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(toList());
    }

    /**
     * 5.3 Search and Match
     */
    public static void match() {
        List<Dish> menu = new ArrayList<>();
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("Vegetarian Menu!");
        }
        boolean isHealthy = menu.stream().noneMatch(d -> d.getCalories() > 1000);

        menu.stream().filter(Dish::isVegetarian).findFirst().ifPresent(d -> System.out.println(d.getName()));
    }

    /**
     * 5.4 Reduce and fold
     */
    public void reduceAndFold() {
        int sum = Arrays.asList(1, 3, 5, 7, 9).stream().reduce(0, Integer::sum);
        System.out.println(sum);

        Optional<Integer> sum2 = Arrays.asList(1, 3, 5, 7, 9).stream().reduce(Integer::sum);
        Optional<Integer> min = Arrays.asList(1, 3, 5, 7, 9).stream().reduce(Integer::min);
    }

    public void transactionTest() {
        Trader a = new Trader("a", "Hoboken");
        Trader b = new Trader("b", "New York City");
        Trader c = new Trader("c", "Jersey City");
        Trader d = new Trader("d", "Jersey City");

        List<Trader> traders = Arrays.asList(a, b, c, d);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(a, 2016, 150, null),
                new Transaction(b, 2015, 700, null),
                new Transaction(c, 2011, 123, null),
                new Transaction(d, 2014, 430, null),
                new Transaction(d, 2013, 500, null)
        );

        //All transaction in 2011, sorted by value
        List<Transaction> result = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(toList());

        //Cities
        List<String> cities = traders.stream().map(Trader::getCity).distinct().collect(toList());

        //All traders from Jersey City and sorted by name
        List<Trader> jcTraders = traders.stream()
                .filter(t -> "Jersey City".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
        ;

        //All names
        List<String> names = traders.stream()
                .map(Trader::getName)
                .sorted()
                .collect(toList());
        String allNames = traders.stream().map(Trader::getName).sorted().reduce("", String::concat);

        //Value sum of Jersey City traders
        int sum = transactions.stream()
                .filter(transaction -> "Jersey City".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);

        //Highest value
        int highest = transactions.stream().map(Transaction::getValue)
                .reduce(0, Integer::max);

        int lowest = transactions.stream().map(Transaction::getValue)
                .reduce(Integer.MAX_VALUE, Integer::min);
    }

    /**
     * 5.6 Number stream
     */
    public void pythagorean() {
        Stream<int[]> p = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.range(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

    }

    /**
     * 5.7 Construct a stream
     */
    public void constructStream() {
        Stream<String> stream = Stream.of("Hello", "World");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();

        //From array
        int sum = Arrays.stream(new int[]{1, 3, 5, 7}).sum();

        //From file
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (IOException ioe) {

        }

        //Infinite Stream
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        Stream.iterate(new int[] {0, 1},
                t -> new int[] { t[1], t[1] + t[0] })
                .limit(10)
                .forEach(t -> System.out.println("[" + t[0] + ", " + t[1] + "]"));

        Stream.generate(Math::random).limit(5).forEach(System.out::println);
    }
}
