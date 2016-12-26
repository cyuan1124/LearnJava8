package com.chefmic.learnjava8.j8ia.chapter9;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cyuan on 12/26/16.
 */
public class Game {

    public static void main(String[] args) {
        List<Resizable> resizableShapes = Arrays.asList(new Square(), new Rectangle(), new Ellipse());
        Utils.paint(resizableShapes);
    }

}
