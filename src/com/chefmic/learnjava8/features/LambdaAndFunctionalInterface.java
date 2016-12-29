package com.chefmic.learnjava8.features;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;

/**
 * Created by cyuan on 12/28/16.
 */
public class LambdaAndFunctionalInterface {

    public static void testLambda() {
        // The type of 'e' is induced by JVM
        Arrays.asList(1, 3, 5, 7, 9).forEach(e -> System.out.println(e));

        Arrays.asList("a", "b", "c").forEach((String e) -> System.out.println(e));

        Arrays.asList("a", "b", "c").forEach((String e) -> {
            System.out.println(e);
            System.out.println(e);
        });

        // Local variable is converted to 'final' as default
        String separator = ", ";
        Arrays.asList("a", "b", "c").forEach((String e) -> System.out.println(e + separator));

        Arrays.asList("a", "b", "d").sort(Comparator.naturalOrder());
    }

    @FunctionalInterface
    public interface Functional {

        void method();

        default void defaultMethod() {

        }

    }

    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaulable create(Supplier<Defaulable> supplier) {
            return supplier.get();
        }
    }

    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());
    }

    public static class Car {
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }

}
