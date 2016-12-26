package com.chefmic.learnjava8.j8ia.chapter8.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyuan on 12/25/16.
 */
public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }

    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("money")) {
                // Work as a financial magazine
            }
        });

        feed.registerObserver((String tweet) -> {
            // As you wish
        });
    }

}
