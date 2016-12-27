package com.chefmic.learnjava8.j8ia.chapter11;

/**
 * Created by cyuan on 12/26/16.
 */
public class Util {

public static void delay() {
    try {
        Thread.sleep(1000L);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}

}
