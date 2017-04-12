// module-info.java
module com.jdojo.logger {    
    requires log4j.api;
    requires log4j.core;

    exports com.jdojo.logger;

    provides java.lang.System.LoggerFinder
        with com.jdojo.logger.Log4jLoggerFinder;
}
