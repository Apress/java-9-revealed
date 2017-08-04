// module-info.java
module com.jdojo.prime.faster {
    requires com.jdojo.prime;
 
    provides com.jdojo.prime.PrimeChecker 
        with com.jdojo.prime.faster.FasterPrimeChecker;
}
