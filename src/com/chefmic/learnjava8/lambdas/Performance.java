package com.chefmic.learnjava8.lambdas;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cyuan on 12/29/16.
 */
public interface Performance {

    public String getName();

    public Stream<Artist> getMusicians();

    default List<String> getAllMusicians() {
        return getMusicians().flatMap(artist -> {
            return artist.getName().startsWith("The") ? Stream.of(artist) : artist.getMembers().stream();
        }).map(artist -> artist.getName()).collect(Collectors.toList());
    }

}
