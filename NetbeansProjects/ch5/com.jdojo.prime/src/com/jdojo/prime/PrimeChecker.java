// PrimeChecker.java
package com.jdojo.prime;

import java.util.List;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;

public interface PrimeChecker {
    /**
     * Returns the service provider name.
     *
     * @return The service provider name
     */
    String getName();

    /**
     * Returns true if the specified number is a prime, false otherwise.
     *
     * @param n The number to be check for being prime
     * @return true if the specified number is a prime, false otherwise.
     */
    boolean isPrime(long n);

    /**
     * Returns the first PrimeChecker service provide found.
     *
     * @return The first PrimeChecker service provide found.
     * @throws RuntimeException When no PrimeChecker service provider is found.
     */
    static PrimeChecker newInstance() throws RuntimeException {        
        return ServiceLoader.load(PrimeChecker.class)
                            .findFirst()
                            .orElseThrow(
                  () -> new RuntimeException("No PrimeChecker service provider found."));                
    }

    /**
     * Returns a PrimeChecker service provider instance by name.
     *
     * @param providerName The prime checker service provider name
     * @return A PrimeChecker
     */
    static PrimeChecker newInstance(String providerName) throws RuntimeException {
        ServiceLoader<PrimeChecker> loader = ServiceLoader.load(PrimeChecker.class);

        for (PrimeChecker checker : loader) {
            if (checker.getName().equals(providerName)) {
                return checker;
            }
        }

        throw new RuntimeException("A PrimeChecker service provider with the name '"
                + providerName + "' was not found.");
    }
    
    static List<PrimeChecker> startsWith(String prefix) {
        return ServiceLoader.load(PrimeChecker.class)
                            .stream()
                            .filter((Provider p) -> p.type().getName().startsWith(prefix))
                            .map(Provider::get)
                            .collect(Collectors.toList());
    }
}
