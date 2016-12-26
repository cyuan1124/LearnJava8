package com.chefmic.learnjava8.j8ia.chapter8.strategy;

/**
 * Created by cyuan on 12/25/16.
 */
public class Validator {

    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }

    public static void main(String[] args) {
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("abcdefg");

        Validator lowerValidator = new Validator(s -> s.matches("[a-z]+"));
        Validator numValidator = new Validator(s -> s.matches("\\d+"));

    }

}
