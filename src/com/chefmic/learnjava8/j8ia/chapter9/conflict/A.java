package com.chefmic.learnjava8.j8ia.chapter9.conflict;

/**
 * Created by cyuan on 12/26/16.
 */
public interface A {

    default void hello() {
        System.out.println("Hello from A");
    }

}
