package com.chefmic.learnjava8.j8ia.chapter3;

/**
 * Created by chenyuan on 12/4/16.
 */
public class Letter {

    public static String addHeader(String text) {
        return "From Rauol, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replace("labda", "lambda");
    }
}
