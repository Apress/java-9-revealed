// FasterPrimeChecker.java
package com.jdojo.prime.faster;

import com.jdojo.prime.PrimeChecker;

public class FasterPrimeChecker implements PrimeChecker {
    private static final String PROVIDER_NAME = "jdojo.faster.primechecker";
    
    // No provider constructor
    private FasterPrimeChecker() {
        // No code
    }
    
    // Define a provider method
    public static PrimeChecker provider() {
        return new FasterPrimeChecker();
    }
    
    @Override
    public boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        long limit = (long) Math.sqrt(n);
        for (long i = 3; i <= limit; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getName() {
        return PROVIDER_NAME;
    }
}
