package com.chefmic.learnjava8.j8ia.chapter9;

import java.util.List;

/**
 * Created by cyuan on 12/26/16.
 */
public class Utils {

    public static void paint(List<Resizable> l) {
        l.forEach(r -> {
            r.setAbsoluteSize(42, 42);
            r.draw();
        });
    }
}
