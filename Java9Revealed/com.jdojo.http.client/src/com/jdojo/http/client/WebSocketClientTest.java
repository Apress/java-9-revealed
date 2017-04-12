// WebSocketClientTest.java
package com.jdojo.http.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class WebSocketClientTest {    
    // Please change the URI to point to your server endpoint
    static final String SERVER_URI = 
                   "ws://localhost:8080/webapp/servertime";
    
    public static void main(String[] args) 
       throws URISyntaxException, InterruptedException {
             
        // Create a client WebSocket
        WebSocketClient wsClient = new WebSocketClient(new URI(SERVER_URI));
        
        // Connect to the Server
        wsClient.connect();
        
        // Wait until the WebSocket is closed
        while(!wsClient.isClosed()) {            
            TimeUnit.SECONDS.sleep(1);
        }

        // Need to exit
        System.exit(0);
    } 
}
