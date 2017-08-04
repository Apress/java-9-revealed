module N {
    requires M;
    provides com.jdojo.prime.PrimeChecker
    with com.jdojo.prime.generic.GenericPrimeChecker;
}