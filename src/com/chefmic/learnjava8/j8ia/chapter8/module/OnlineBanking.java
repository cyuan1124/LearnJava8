package com.chefmic.learnjava8.j8ia.chapter8.module;

/**
 * Created by cyuan on 12/25/16.
 */
abstract class OnlineBanking {

    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    abstract void makeCustomerHappy(Customer c);


}
