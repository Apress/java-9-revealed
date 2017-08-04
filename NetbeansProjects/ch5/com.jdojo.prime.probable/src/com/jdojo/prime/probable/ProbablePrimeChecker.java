// ProbablePrimeChecker.java
package com.jdojo.prime.probable;

import com.jdojo.prime.PrimeChecker;
import java.math.BigInteger;

public interface ProbablePrimeChecker {  
    // A provider method
    public static PrimeChecker provider() {
        final String PROVIDER_NAME = "jdojo.probable.primechecker";
        
        return new PrimeChecker() {
            @Override
            public boolean isPrime(long n) {
                // Use 1000 for high certainty, which is an arbitray big number I chose
                int certainty = 1000;
                return BigInteger.valueOf(n).isProbablePrime(certainty);
            }
            
            @Override
            public String getName() {
                return PROVIDER_NAME;
            }            
        };
    }
}
