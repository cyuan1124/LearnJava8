package com.chefmic.learnjava8.j8ia.chapter6;

import com.chefmic.learnjava8.j8ia.object.Currency;
import com.chefmic.learnjava8.j8ia.object.Dish;
import com.chefmic.learnjava8.j8ia.object.Transaction;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * Created by chenyuan on 12/5/16.
 */
public class CollectorsDemo {

    /**
     * 6.1 Map transactions by currency
     * @param transactions to group
     * @return a map of currency to list of transactions
     */
    public Map<Currency, List<Transaction>> groupTransactions(List<Transaction> transactions) {
        Map<Currency, List<Transaction>> currencyTransactionMap = new HashMap<>();
        for (Transaction t : transactions) {
            Currency currency = t.getCurrency();
            List<Transaction> currencyTransactionList = currencyTransactionMap.get(currency);
            if (currencyTransactionList == null) {
                currencyTransactionList = new ArrayList<>();
                currencyTransactionMap.put(currency, currencyTransactionList);
            }
            currencyTransactionList.add(t);
        }

        //Or use stream
        currencyTransactionMap = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        return currencyTransactionMap;
    }

    /**
     * 6.2 Stream reduce on menus
     */
    public void operatingMenu(List<Dish> menu) {
        long menuItemsCount = menu.stream().collect(counting());
        menuItemsCount = menu.stream().count();

        Optional<Dish> maxCalorie = menu.stream().max(Comparator.comparingInt(Dish::getCalories));
        Optional<Dish> minCalorie = menu.stream().min(Comparator.comparingInt(Dish::getCalories));

        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));

        IntSummaryStatistics summary = menu.stream().collect(summarizingInt(Dish::getCalories));

        String names = menu.stream().map(Dish::getName).collect(joining(", "));

        //Use reduce()
        totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
    }

    /**
     * 6.1 Test
     */
    public void test(List<Dish> menu) {
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        shortMenu = menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
    }

    /**
     * 6.3 Group
     */
    public void group(List<Dish> menu) {
        Map<Dish.Type, List<Dish>> submenus = menu.stream().collect(groupingBy(Dish::getType));
        Map<Dish.CaloricLevel, List<Dish>> healthyMenu = menu.stream().collect(groupingBy(d -> {
            if (d.getCalories() <= 400) return Dish.CaloricLevel.Low;
            else if (d.getCalories() <= 700) return Dish.CaloricLevel.Normal;
            return Dish.CaloricLevel.High;
        }));

        //Multi-level group
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> fullMenus = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(
                        d -> {
                            if (d.getCalories() <= 400) return Dish.CaloricLevel.Low;
                            else if (d.getCalories() <= 700) return Dish.CaloricLevel.Normal;
                            return Dish.CaloricLevel.High;
                        }
                )));

        Map<Dish.Type, Long> sizeMenu = menu.stream().collect(groupingBy(Dish::getType, counting()));
        Map<Dish.Type, Optional<Dish>> mostCalorieMenu = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));

        Map<Dish.Type, Dish> mostCalMenu = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));

        Map<Dish.Type, Set<Dish.CaloricLevel>> typCaloricLevelMap = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(d -> {
                    if (d.getCalories() < 400) return Dish.CaloricLevel.Low;
                    if (d.getCalories() < 700) return Dish.CaloricLevel.Normal;
                    return Dish.CaloricLevel.High;
                }, toSet())));
    }

    /**
     * 6.4 Partition
     */
    public void partition(List<Dish> menu) {
        Map<Boolean, List<Dish>> partitionMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegMenuByType = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

        Map<Boolean, Dish> highestCaloriesDish = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }

    public static boolean isPrime(int n) {
        int root = (int) Math.sqrt(n);
        return IntStream.range(2, n).noneMatch(i -> root % i == 0);
    }

    public Map<Boolean, List<Integer>> partitionInteger(int n) {
        return IntStream.range(2, n).boxed().collect(partitioningBy(i -> isPrime(n)));
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        return takeWhile(primes, i -> i <= (int) Math.sqrt(candidate)).stream().noneMatch(p -> candidate % p == 0);

    }

    public static<A> List<A> takeWhile(List<A> list, Predicate<A> p) {
        int i = 0;
        for (A item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

}
