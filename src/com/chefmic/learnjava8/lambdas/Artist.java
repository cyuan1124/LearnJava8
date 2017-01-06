package com.chefmic.learnjava8.lambdas;

import java.util.List;

/**
 * Created by cyuan on 12/29/16.
 */
public class Artist {

    private final String name;
    private final List<Artist> members;
    private final String origin;
    private final String nationality;

    public String getName() {
        return name;
    }

    public List<Artist> getMembers() {
        return members;
    }

    public Artist(String name, List<Artist> members, String origin, String nationality) {

        this.name = name;
        this.members = members;
        this.origin = origin;
        this.nationality = nationality;
    }

    public String getOrigin() {
        return origin;
    }

    public String getNationality() {

        return nationality;
    }

    public boolean isFrom(String origin) {
        return this.origin.equals(origin);
    }
}
