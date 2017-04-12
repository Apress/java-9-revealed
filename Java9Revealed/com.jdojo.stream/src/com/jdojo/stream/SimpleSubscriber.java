// SimpleSubscriber.java
package com.jdojo.stream;

import java.util.concurrent.Flow;

public class SimpleSubscriber implements Flow.Subscriber<Long> {    
    private Flow.Subscription subscription;
    
    // Subscriber name
    private String name = "Unknown";
    
    // Maximum number of items to be processed by this subscriber
    private final long maxCount;
    
    // keep track of number of items processed
    private long counter;
    
    public SimpleSubscriber(String name, long maxCount) {
        this.name = name;
        this.maxCount = maxCount <= 0 ? 1 : maxCount;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        System.out.printf("%s subscribed with max count %d.%n", name, maxCount);        
        
        // Request all items in one go
        subscription.request(maxCount);
    }

    @Override
    public void onNext(Long item) {
        counter++;
        System.out.printf("%s received %d.%n", name, item);
        
        if (counter >= maxCount) {
            System.out.printf("Cancelling %s. Processed item count: %d.%n", name, counter);            

            // Cancel the subscription
            subscription.cancel();
        } 
    }

    @Override
    public void onError(Throwable t) {
        System.out.printf("An error occurred in %s: %s.%n", name, t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.printf("%s is complete.%n", name);
    }
}
