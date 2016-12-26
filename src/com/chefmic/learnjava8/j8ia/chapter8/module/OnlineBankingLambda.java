package com.chefmic.learnjava8.j8ia.chapter8.module;

import java.util.function.Consumer;

/**
 * Created by cyuan on 12/25/16.
 */
public class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

}
