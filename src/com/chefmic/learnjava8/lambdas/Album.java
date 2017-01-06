package com.chefmic.learnjava8.lambdas;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by cyuan on 12/29/16.
 */
public class Album {

    private final String name;

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    private final List<Track> tracks;

    private final List<Artist> musicians;

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        this.name = name;
        this.tracks = tracks;
        this.musicians = musicians;
    }

    public Stream<Artist> getMusicians() {
        return musicians.stream();
    }
}
