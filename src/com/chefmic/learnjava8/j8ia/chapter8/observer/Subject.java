package com.chefmic.learnjava8.j8ia.chapter8.observer;

/**
 * Created by cyuan on 12/25/16.
 */
interface Subject {

    void registerObserver(Observer observer);

    void notifyObservers(String tweet);

}
