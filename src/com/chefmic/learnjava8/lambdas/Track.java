package com.chefmic.learnjava8.lambdas;

/**
 * Created by cyuan on 12/29/16.
 */
public class Track {

    private final String name;

    private final int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}
