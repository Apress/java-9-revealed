// module-info.java
module com.jdojo.prime.probable{
    requires com.jdojo.prime;
 
    provides com.jdojo.prime.PrimeChecker 
        with com.jdojo.prime.probable.ProbablePrimeChecker;
}
