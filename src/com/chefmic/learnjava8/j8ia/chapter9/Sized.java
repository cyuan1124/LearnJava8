package com.chefmic.learnjava8.j8ia.chapter9;

/**
 * Created by cyuan on 12/26/16.
 */
public interface Sized {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

}
