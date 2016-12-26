package com.chefmic.learnjava8.j8ia.chapter10;

import java.util.Optional;

/**
 * Created by cyuan on 12/26/16.
 */
public class Agent {

    public String getInsuranceName(Insurance insurance) {
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);
        return name.get();
    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance();
    }

    public static void main(String[] args) {
        Optional<Insurance> insurance = Optional.empty();
        insurance.filter(i -> "CambridgeInsurance".equals(i.getName()))
                .ifPresent(x -> System.out.println("OK"));
    }
}
