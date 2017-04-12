// TimeServerEndPoint.java
package com.jdojo.ws;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import javax.websocket.CloseReason;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import static javax.websocket.CloseReason.CloseCodes.NORMAL_CLOSURE;

@ServerEndpoint("/servertime")
public class TimeServerEndPoint {
    @OnOpen
    public void onOpen(Session session) {
        session.setMaxTextMessageBufferSize(200);
        System.out.println("Client connected. ");
    }
    
    @OnClose
    public void onClose(Session session) {        
        System.out.println("Connection closed.");
    }
    
    @OnError
    public void onError(Session session, Throwable t) { 
        System.out.println("Error occurred:" + t.getMessage());
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Client: " + message);                
        
        // Send messages to the client
        sendMessages(session);
    } 
    
    private void sendMessages(Session session) {
        /* Start a new thread and send 3 messages to the 
           client. Each message contains the current date and 
           time with zone.
        */
        new Thread(() -> {
            for(int i = 0; i < 3; i++) {
                String currentTime = ZonedDateTime.now().toString();
                try {
                    session.getBasicRemote()
                           .sendText(currentTime, true);
                    System.out.println("Sent: " + currentTime);
                    TimeUnit.SECONDS.sleep(5);
                } catch(InterruptedException | IOException e) {
                    e.printStackTrace();
                    break;
                } 
            }
            
            try {
                // Let us close the WebSocket
                session.close(new CloseReason(NORMAL_CLOSURE, "Done"));
                System.out.println("Sent a closure message");
            } catch (IOException e) {
                e.printStackTrace();
            }
        })
        .start();
    }
}
