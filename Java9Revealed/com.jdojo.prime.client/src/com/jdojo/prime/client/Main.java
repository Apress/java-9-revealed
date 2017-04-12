// Main.java
package com.jdojo.prime.client;

import com.jdojo.prime.PrimeChecker;

public class Main {
    public static void main(String[] args) {        
        // Numbers to be checked for prime
        long[] numbers = {3, 4, 121, 977};

        // Try a default prime checker service provider
        try {
            PrimeChecker checker = PrimeChecker.newInstance();
            checkPrimes(checker, numbers);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // Try the faster prime checker
        try {
            PrimeChecker checker = PrimeChecker.newInstance("jdojo.faster.primechecker");
            checkPrimes(checker, numbers);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        
        // Try the probable prime checker
        try {
            PrimeChecker checker = PrimeChecker.newInstance("jdojo.probable.primechecker");
            checkPrimes(checker, numbers);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkPrimes(PrimeChecker primeChecker, long... numbers) {
        System.out.format("Using %s:%n",  primeChecker.getName());

        for (long n : numbers) {
            if (primeChecker.isPrime(n)) {
                System.out.printf("%d is a prime.%n", n);
            } else {
                System.out. printf("%d is not a prime.%n", n);
            }
        }
    }
}
