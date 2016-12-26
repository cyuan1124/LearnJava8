package com.chefmic.learnjava8.j8ia.chapter9;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by cyuan on 12/26/16.
 */
public interface MyCollection<E> extends Collection<E> {

    default boolean removeIf(Predicate<? super E> filter) {
        boolean removed = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

}
