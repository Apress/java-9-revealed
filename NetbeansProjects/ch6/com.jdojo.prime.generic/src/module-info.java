// module-info.java
module com.jdojo.prime.generic {       
    requires com.jdojo.prime;    

    provides com.jdojo.prime.PrimeChecker 
        with com.jdojo.prime.generic.GenericPrimeChecker;
}
