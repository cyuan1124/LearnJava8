package com.chefmic.learnjava8.j8ia.chapter9.conflict;

/**
 * Created by cyuan on 12/26/16.
 */
public class C implements A, B {

    public void hello() {
        B.super.hello();
    }

    public static void main(String[] args) {
        new C().hello();
    }
}
