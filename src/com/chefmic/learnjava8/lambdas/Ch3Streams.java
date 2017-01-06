package com.chefmic.learnjava8.lambdas;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cyuan on 12/29/16.
 */
public class Ch3Streams {

    private static void countLondonArtists(List<Artist> artists) {
        // Use for-each loop
        long count = 0;
        for (Artist artist : artists) {
            if (artist.isFrom("London")) {
                count++;
            }
        }

        // Use Iterator
        count = 0;
        Iterator<Artist> iterator = artists.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isFrom("London")) {
                count++;
            }
        }

        // Use stream, inner iteration
        count = artists.stream().filter(artist -> artist.isFrom("London")).count();
    }

    private static void implementation() {
        List<Artist> artists = Arrays.asList();

        // Only filter, no count. Actually stream not executed
        artists.stream().filter(artist -> artist.isFrom("London"));
    }

    private static void operations() {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());

        // Use for-each loop
        for (String string : Arrays.asList("a", "b", "hello")) {
            String upperCase = string.toUpperCase();
            collected.add(upperCase);
        }

        collected = Stream.of("a", "b", "hello").map(s -> s.toUpperCase()).collect(Collectors.toList());

        // Use loop as filter
        for (String val : Arrays.asList("a", "abc", "abc1")) {
            if (Character.isDigit(val.charAt(0))) {
                collected.add(val);
            }
        }

        collected = Arrays.asList("a", "1abc", "abc1").stream()
                .filter(s -> Character.isDigit(s.charAt(0)))
                .collect(Collectors.toList());

        // Flatmap
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());

        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));


        Track shortestTrack = tracks.stream().min(Comparator.comparing(Track::getLength)).get();
    }

    private static void reduceMode(Collection<Object> collection, Object initialValue) {
        Object accumulator = initialValue;
        for (Object element : collection) {
            accumulator = combine(accumulator, element);
        }
    }

    private static Object combine(Object accumulator, Object element) {
        return accumulator;
    }

    private static void reduceOperation() {
        int count = Stream.of(1, 2, 3).reduce(0, (acc, element) -> acc + element);
    }

    private static void getAllNationalities(Album album) {
        album.getMusicians().filter(artist -> artist.getName().startsWith("The"))
                .map(Artist::getNationality)
                .collect(Collectors.toSet());
    }

    public static void printTrackLengthStats(Album album) {
        IntSummaryStatistics trackLengthStats = album.getTracks().stream().mapToInt(Track::getLength).summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());
    }

    private static void overloadedMethod(Object o) {
        System.out.println("Object");
    }

    private static void overloadedMethod(String s) {
        System.out.println("String");
    }
}
