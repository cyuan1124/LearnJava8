package com.chefmic.learnjava8.j8ia.chapter8.module;

/**
 * Created by cyuan on 12/25/16.
 */
public class Database {

    public static Customer getCustomerWithId(int id) {
        return new Customer(id);
    }

}
