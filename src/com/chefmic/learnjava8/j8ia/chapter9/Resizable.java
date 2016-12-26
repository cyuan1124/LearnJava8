package com.chefmic.learnjava8.j8ia.chapter9;

/**
 * Created by cyuan on 12/26/16.
 */
public interface Resizable {

    int getWidth();

    int getHeight();

    void setWidth(int width);

    void setHeight(int height);

    void setAbsoluteSize(int width, int height);

    void draw();

    default void setRelativeSize(int wFactor, int hFactor) {
        setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
    }

}
