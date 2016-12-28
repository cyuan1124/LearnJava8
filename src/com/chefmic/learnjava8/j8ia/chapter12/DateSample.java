package com.chefmic.learnjava8.j8ia.chapter12;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * Created by cyuan on 12/27/16.
 */
public class DateSample {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2016, 12, 27);
        int year = date.getYear();
        Month month = date.getMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();
        System.out.println("Year " + year + " Month " + month.name() + " Day Of Week " + dow);
        System.out.println("Month length " + len + " Leap Year ? " + leap);

        LocalDate today = LocalDate.now();
        year = today.get(ChronoField.YEAR);
        int m = today.get(ChronoField.MONTH_OF_YEAR);
        int day = today.get(ChronoField.DAY_OF_MONTH);
    }

    public static void localTimeTest() {
        LocalTime time = LocalTime.of(13, 45, 50);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        LocalDate date = LocalDate.parse("2016-12-28");
        time = LocalTime.parse("13:45:20");
    }

    public static void localDateTimeTest() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        LocalDate date1 = dt5.toLocalDate();
        LocalTime time1 = dt5.toLocalTime();
    }

    public static void dateFormatTest() {
        Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000);
        Instant.ofEpochSecond(4, -1_000_000_000);
    }

    public static void durationTest() {
        Duration d1 = Duration.between(LocalTime.of(12, 34), LocalTime.now());

        Period period = Period.between(LocalDate.now(), LocalDate.of(2014, 3, 8));

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMins = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

    public static void modifyDate() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);

        LocalDate date5 = date1.plusWeeks(1);
        LocalDate date6 = date5.minusYears(2);
    }

    public static void modifyDateTest() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        date.withYear(2011);
        System.out.println(date.toString());
    }

    public static void temporalAdjuster() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date3 = date2.with(lastDayOfMonth());
    }
}
