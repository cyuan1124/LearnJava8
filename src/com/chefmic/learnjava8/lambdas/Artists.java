package com.chefmic.learnjava8.lambdas;

import java.util.List;
import java.util.Optional;

/**
 * Created by cyuan on 12/29/16.
 */
public class Artists {

    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    private void indexException(int index) {
        throw new IllegalArgumentException(index + " doesn't coorespond to an Artist");
    }

    public String getArtistName(int index) {
        Optional<Artist> artist = getArtist(index);
        return artist.isPresent() ? artist.get().getName() : "unknown";
    }
}
