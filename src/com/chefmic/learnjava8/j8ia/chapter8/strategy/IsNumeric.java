package com.chefmic.learnjava8.j8ia.chapter8.strategy;

/**
 * Created by cyuan on 12/25/16.
 */
public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
