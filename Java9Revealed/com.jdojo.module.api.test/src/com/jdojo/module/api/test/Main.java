// Main.java
package com.jdojo.module.api.test;

import com.jdojo.module.api.UpdateModule;
import com.jdojo.prime.PrimeChecker;

public class Main {
    public static void main(String[] args) {
        long[] numbers = {3, 10};
        
        try {
            // Obtain a service provider for the com.jdojo.prime.PrimeChecker service type
            PrimeChecker pc = UpdateModule.findFirstService(PrimeChecker.class);
            
            // Check a few numbers for prime
            for (long n : numbers) {
                boolean isPrime = pc.isPrime(n);
                System.out.printf("%d is a prime: %b%n", n, isPrime);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
