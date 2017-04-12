// CalculatorTest.java
package com.jdojo.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import static jdk.incubator.http.HttpRequest.BodyProcessor.fromString;
import jdk.incubator.http.HttpResponse;

public class CalculatorTest {
    public static void main(String[] args) {
        try {
            URI calcUri = 
                new URI("http://localhost:8080/webapp/Calculator");

            String formData = "n1=" + URLEncoder.encode("10","UTF-8") +
                              "&n2=" + URLEncoder.encode("20","UTF-8") + 
                              "&op=" + URLEncoder.encode("+","UTF-8")  ;

            // Create a request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(calcUri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "text/plain")                
                .POST(fromString(formData))
                .build();
            
            // Process the response asynchronously. When the response
            // is ready the processResponse() method of this class will
            // be called.
            HttpClient.newHttpClient()
                      .sendAsync(request, 
                                 HttpResponse.BodyHandler.asString())
                      .whenComplete(CalculatorTest::processResponse);

            try {
                // Let the current thread sleep for 5 seconds,
                // so the async response processing is complete
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void processResponse(HttpResponse<String> response, 
                                       Throwable t) {

         if (t == null ) {
             System.out.println("Response Status Code: " +  
                                 response.statusCode());
             System.out.println("Response Body: " + response.body());
         } else {
            System.out.println("An exception occurred while " +
                "processing the HTTP request. Error: " +  t.getMessage());
         }
     }
}
