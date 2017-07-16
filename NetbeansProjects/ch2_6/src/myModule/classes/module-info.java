module myModule {
// Exports the packages - com.jdojo.util and
// com.jdojo.util.parser
exports com.jdojo.util;
exports com.jdojo.util.parser;
// Reads the java.sql module
requires java.sql;
// Opens com.jdojo.legacy package for reflective access
opens com.jdojo.legacy;
// Uses the service interface java.sql.Driver
uses java.sql.Driver;
// Provides the com.jdojo.util.parser.FasterCsvParser
// class as an implementation for the service interface
// named com.jdojo.util.CsvParser
provides com.jdojo.util.CsvParser
with com.jdojo.util.parser.FasterCsvParser;
}