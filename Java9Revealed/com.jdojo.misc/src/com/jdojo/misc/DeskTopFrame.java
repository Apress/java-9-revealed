// DeskTopFrame.java
package com.jdojo.misc;

import java.awt.Desktop;
import java.awt.desktop.UserSessionEvent;
import java.awt.desktop.UserSessionListener;
import java.util.concurrent.TimeUnit;

public class DeskTopFrame {
    public static void main(String[] args) {
        // Check if Desktop class is available
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Current Platform does not support Desktop.");
            return;
        }

        System.out.println("Current platform supports Desktop.");

        // Get the desktop reference
        Desktop desktop = Desktop.getDesktop();

        // Check if user session event notification is supported
        if (!desktop.isSupported(Desktop.Action.APP_EVENT_USER_SESSION)) {
            System.out.println("User session notification is not " + 
                               "supported by the current desktop");
            return;
        }

        System.out.println("Lock and unlock your session to see " + 
                           "user session change notification in action.");

        // Add an event handler for a change in user session
        desktop.addAppEventListener(new UserSessionListener() {
            @Override
            public void userSessionDeactivated(UserSessionEvent e) {
                System.out.println("User session deactivated. Reason: " + e.getReason());
            }

            @Override
            public void userSessionActivated(UserSessionEvent e) {
                System.out.println("User session activated. Reason: " + e.getReason());
            }
        });

        // Make the current thread sleep for 2 minutes
        try {            
            TimeUnit.SECONDS.sleep(120);            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
