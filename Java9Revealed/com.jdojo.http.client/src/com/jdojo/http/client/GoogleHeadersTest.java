// GoogleHeadersTest.java
package com.jdojo.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

public class GoogleHeadersTest {
    public static void main(String[] args) {
        try {
            URI googleUri = new URI("http://www.google.com");            
            HttpClient client = HttpClient.newHttpClient();
            
            HttpRequest request = HttpRequest.newBuilder(googleUri)
                            .method("HEAD", HttpRequest.noBody())
                            .build();
            
            HttpResponse<?> response = 
                    client.send(request, HttpResponse.BodyHandler.discard(null));
                 

            // Print the response status code and headers
            System.out.println("Response Status Code:" + response.statusCode());

            System.out.println("Response Headers are:");
            response.headers()
                    .map()
                    .entrySet()
                    .forEach(System.out::println);
            
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
