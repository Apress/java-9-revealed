// WebSocketClient.java
package com.jdojo.http.client;

import java.net.URI;
import java.util.concurrent.CompletionStage;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.WebSocket;

public class WebSocketClient {
    private WebSocket webSocket;
    private final URI serverUri;
    
    private boolean inError = false;
    
    public WebSocketClient(URI serverUri) {
        this.serverUri = serverUri;
    }

    public boolean isClosed() {
        return (webSocket != null && webSocket.isClosed())
               || 
               this.inError;        
    }
    
    public void connect() {
        HttpClient.newHttpClient()
                  .newWebSocketBuilder(serverUri, this.getListener())
                  .buildAsync()
                  .whenComplete(this::statusChanged);
    }

    private void statusChanged(WebSocket webSocket, Throwable t) {
        this.webSocket = webSocket;
           
        if (t == null) {        
            this.talkToServer();
        } else {
            this.inError = true;
            System.out.println("Could not connect to the server." + 
                               " Error: " + t.getMessage());
        }
    }
    
    private void talkToServer() {
         // Allow one message to be received by the listener
         webSocket.request(1);
                
        // Send the server a request for time
        webSocket.sendText("Hello");
        
    }

    private WebSocket.Listener getListener() {
        return new WebSocket.Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                // Allow one more message to be received by the listener
                webSocket.request(1);
                
                // Notify the user that we are connected
                System.out.println("A WebSocket has been opened.");                
            }

            @Override
            public CompletionStage<?> onClose(WebSocket webSocket, 
                                         int statusCode, String reason) {
                // Server closed the web socket. Let us respond to 
                // the close message from the server
                webSocket.sendClose();
                
                System.out.println("The WebSocket is closed." +
                                   " Close Code: " + statusCode + 
                                   ", Close Reason: " + reason);
                
                // Return null indicating that this webSocket
                // can be closed immediately
                return null;
            }
            
            @Override
            public void onError(WebSocket webSocket, Throwable t) {
                System.out.println("An error occurred: " + t.getMessage());
            }

            @Override
            public CompletionStage<?> onText(WebSocket WebSocket, 
                CharSequence message, WebSocket.MessagePart part) {

                // Allow one more message to be received by the listener
                webSocket.request(1);
                
                // Print the message received from the server
                System.out.println("Server: " + message);
                
                // Return null indicating that we are done 
                // processing this message
                return null;
            }
        }; 
    }
}
