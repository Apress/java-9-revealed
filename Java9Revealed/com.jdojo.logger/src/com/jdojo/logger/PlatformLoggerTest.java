// PlatformLoggerTest.java
package com.jdojo.logger;

import java.lang.System.Logger;
import static java.lang.System.Logger.Level.TRACE;
import static java.lang.System.Logger.Level.ERROR;
import static java.lang.System.Logger.Level.INFO;
import java.util.Currency;
import java.util.Set;

public class PlatformLoggerTest {    
    public static void main(final String... args) {
        // Let us load all currencies   
        Set<Currency> c = Currency.getAvailableCurrencies();
        System.out.println("# of currencies: " + c.size());
        
        /* Let us test the platform logger by logging few messages */
        Logger logger = System.getLogger("Log4jLogger");
        logger.log(TRACE, "Entering application.");
        logger.log(ERROR, "An unknown error occurred.");
        logger.log(INFO, "FYI");
        logger.log(TRACE, "Exiting application.");
    }
}
