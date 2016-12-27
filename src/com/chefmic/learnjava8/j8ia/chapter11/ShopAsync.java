package com.chefmic.learnjava8.j8ia.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by cyuan on 12/26/16.
 */
public class ShopAsync {

    List<Shop> shops = Arrays.asList(new Shop("Target"),
            new Shop("BestBuy"), new Shop("Shoprite"), new Shop("Amazon"));

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))))
                .collect(Collectors.toList());
    }

}
