package com.chefmic.learnjava8.j8ia.chapter8.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by cyuan on 12/25/16.
 */
public class Factory {


    final static Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("load", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product createProduct(String name) {
        Supplier<Product> supplier = map.get(name);
        if (supplier != null) {
            return supplier.get();
        }
        throw new IllegalArgumentException("No such product " + name);
    }

}
