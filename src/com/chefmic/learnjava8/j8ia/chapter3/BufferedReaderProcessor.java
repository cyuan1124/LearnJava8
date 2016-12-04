package com.chefmic.learnjava8.j8ia.chapter3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by chenyuan on 12/4/16.
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader b) throws IOException;

}
