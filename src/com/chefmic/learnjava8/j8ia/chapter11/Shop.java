package com.chefmic.learnjava8.j8ia.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by cyuan on 12/26/16.
 */
public class Shop {

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    private final String name;

    public Shop(String name) {
        this.name = name;
    }

    static List<Shop> shops = Arrays.asList(new Shop("Target"),
            new Shop("BestBuy"), new Shop("Shoprite"), new Shop("Amazon"));

    public List<String> findPrices(String product) {
        return shops.parallelStream().map(shop -> shop.getPrice(product))
                .collect(Collectors.toList());
    }

    public List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product), executor))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Shop shop = new Shop("Bestbuy");
        long start = System.nanoTime();
        System.out.println(shop.findPrices("Pixel XL"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Done in " + duration + " msecs");
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
        Util.delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

}
