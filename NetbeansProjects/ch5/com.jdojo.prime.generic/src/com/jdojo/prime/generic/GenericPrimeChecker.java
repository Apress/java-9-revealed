// GenericPrimeChecker.java
package com.jdojo.prime.generic;

import com.jdojo.prime.PrimeChecker;

public class GenericPrimeChecker implements PrimeChecker {
    private static final String PROVIDER_NAME = "jdojo.generic.primechecker";
    
    @Override
    public boolean isPrime(long n) {
        if(n <= 1) {
            return false;
        }
        
        if (n == 2) {
            return true;
        }
        
        if(n%2 == 0) {
            return false;
        }
        
        for(long i = 3; i < n; i += 2) {
            if(n%i == 0) {
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
