// Test.java
package com.jdojo.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import jdk.incubator.http.HttpResponse.BodyHandler;
import jdk.incubator.http.WebSocket;

public class Test {

    public static void main(String[] args) throws InterruptedException, URISyntaxException, IOException {
        goGoogle();
        //oneShot();

     }

    
    public static void goGoogle() throws InterruptedException, URISyntaxException, IOException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://www.google.com"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, BodyHandler.asString());
        int status = response.statusCode();
        String body = response.body();
        System.out.println("Status: " + status);
        //System.out.println("Status: " + body);

        response = client.send(request, BodyHandler.asString());
        status = response.statusCode();
        body = response.body();
        System.out.println("Status: " + status);
        //System.out.println("Status: " + body);
    }

    public static void oneShot() throws URISyntaxException, IOException, InterruptedException {
        String responseBody = HttpClient.newHttpClient()
                .send(HttpRequest.newBuilder(new URI("https://www.google.com/"))
                        .GET()
                        .build(),
                        BodyHandler.asString())
                .body();
        System.out.println("Status: " + responseBody);

    }
    
}
