// TimeApiTest.java
package com.jdojo.misc;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeApiTest {
    public static void main(String[] args) {
        durationTest();
        testOfInstant();
        testToEpochSecond();
        testDatesUntil();
        testNewFormattingSymbols();
    }

    public static void durationTest() {
        System.out.println("Testing the Duration class...");
        
        // Create a duration of 23 days, 3 hours, 45 minutes, and 30 seconds
        Duration compTime = Duration.ofDays(23)
                .plusHours(3)
                .plusMinutes(45)
                .plusSeconds(30);

        System.out.println("Duration: " + compTime);

        System.out.println("\nDividing a Duration:");

        long wholeDays = compTime.dividedBy(Duration.ofDays(1));
        long wholeWeeks = compTime.dividedBy(Duration.ofDays(7));
        long wholeHours = compTime.dividedBy(Duration.ofHours(7));
        
        // Dividing a Duration By Another Duration
        System.out.println("Number of whole days: " + wholeDays);
        System.out.println("Number of whole weeks: " + wholeWeeks);
        System.out.println("Number of whole hours: " + wholeHours);
        
        // Converting and Retrieving Duration Parts
        System.out.println("\nConverting and Retrieving Duration Parts:");
        System.out.println("toDays(): " + compTime.toDays());
        System.out.println("toDaysPart(): " + compTime.toDaysPart());
        System.out.println("toHours(): " + compTime.toHours());
        System.out.println("toHoursPart(): " + compTime.toHoursPart());
        System.out.println("toMinutes(): " + compTime.toMinutes());
        System.out.println("toMinutesPart(): " + compTime.toMinutesPart());
        
        // Truncating Duration
        System.out.println("\nTruncating Duration:");
        System.out.println("Truncated to DAYS: " + compTime.truncatedTo(ChronoUnit.DAYS));
        System.out.println("Truncated to HOURS: " + compTime.truncatedTo(ChronoUnit.HOURS));
        System.out.println("Truncated to MINUTES: " + compTime.truncatedTo(ChronoUnit.MINUTES));
    }

    public static void testOfInstant() {
        System.out.println("\nTesting the ofInstant() method ...");
        System.out.println("Converting a java.util.Date to a LocalDate:");
        
        // In JDK 8
        Date dt = new Date();
        LocalDate ld = dt.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        System.out.println("Current Local Date: " + ld);
        
        // In JDK 9
        LocalDate ld2 = LocalDate.ofInstant(dt.toInstant(), ZoneId.systemDefault());
        System.out.println("Current Local Date: " + ld2);
        System.out.println("\nCreating a LocalDate and a LocalTime from an Instant:");
        
        // In JDK 8
        Instant now = Instant.now();
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zdt = now.atZone(zone);
        LocalDate ld3 = zdt.toLocalDate();
        LocalTime lt3 = zdt.toLocalTime();

        System.out.println("Local Date: " + ld3 + ", Local Time:" + lt3);
        
        // In JDK 9
        LocalDate ld4 = LocalDate.ofInstant(now, zone);
        LocalTime lt4 = LocalTime.ofInstant(now, zone);
        System.out.println("Local Date: " + ld4 + ", Local Time:" + lt4);
    }

    public static void testToEpochSecond() {
        System.out.println("\nTesting the toEpochSecond() method...");
        LocalDate ld = LocalDate.of(2017, 2, 12);
        LocalTime lt = LocalTime.of(9, 15, 45);
        ZoneOffset offset = ZoneOffset.ofHours(6);
        OffsetTime ot = OffsetTime.of(lt, offset);
        long s1 = ld.toEpochSecond(lt, offset);
        long s2 = lt.toEpochSecond(ld, offset);
        long s3 = ot.toEpochSecond(ld);

        System.out.println("LocalDate.toEpochSecond(): " + s1);
        System.out.println("LocalTime.toEpochSecond(): " + s2);
        System.out.println("OffsetTime.toEpochSecond(): " + s3);
    }

    public static void testDatesUntil() {
        System.out.println("\nTesting stream of Localdates...");
        long sundaysIn2017 = LocalDate.of(2017, 1, 1)
                .datesUntil(LocalDate.of(2018, 1, 1))
                .filter(ld -> ld.getDayOfWeek() == DayOfWeek.SUNDAY)
                .count();

        System.out.println("Number of Sundays in 2017: " + sundaysIn2017);
        
        System.out.println("\nFridays that fall on 13th of the month between 2017 - 2021: ");
        LocalDate.of(2017, 1, 1)
                .datesUntil(LocalDate.of(2022, 1, 1))
                .filter(ld -> ld.getDayOfMonth() == 13 && ld.getDayOfWeek() == DayOfWeek.FRIDAY)
                .forEach(System.out::println);

        System.out.println("\nLast Day of months in 2017:");
        LocalDate.of(2017, 1, 31)
                .datesUntil(LocalDate.of(2018, 1, 1), Period.ofMonths(1))
                .map(ld -> ld.format(DateTimeFormatter.ofPattern("EEE MMM dd, yyyy")))
                .forEach(System.out::println);
    }

    public static void testNewFormattingSymbols() {
        System.out.println("\nTesting new formatting symbols...");
        System.out.println("Using the modified Julian day formatter");
        ZonedDateTime zdt = ZonedDateTime.now();
        
        System.out.println("Current ZonedDateTime: " + zdt);
        System.out.println("Modified Julian Day (g): "
                + zdt.format(DateTimeFormatter.ofPattern("g")));
        
        System.out.println("Modified Julian Day (ggg): "
                + zdt.format(DateTimeFormatter.ofPattern("ggg")));
        
        System.out.println("Modified Julian Day (gggggg): "
                + zdt.format(DateTimeFormatter.ofPattern("gggggg")));

        System.out.println("\nUsing the generic time zone names formatter:");
        
        //ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println("Current ZonedDateTime: " + zdt);
        System.out.println("Using VV: "
                + zdt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm VV")));
        System.out.println("Using z: "
                + zdt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm z")));
        System.out.println("Using zzzz: "
                + zdt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm zzzz")));
        System.out.println("Using v: "
                + zdt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm v")));
        System.out.println("Using vvvv: "
                + zdt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm vvvv")));
    }
}
