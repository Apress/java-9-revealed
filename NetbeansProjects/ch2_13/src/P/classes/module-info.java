module P {
    uses com.jdojo.CsvParser;
    
    provides com.jdojo.CsvParser
        with com.jdojo.CsvParserImpl;

    requires M;
    provides com.jdojo.prime.PrimeChecker
        with com.jdojo.prime.generic.FasterPrimeChecker,
            com.jdojo.prime.generic.SlowPrimeChecker,
            com.jdojo.prime.generic.StabPrimeChecker;
}