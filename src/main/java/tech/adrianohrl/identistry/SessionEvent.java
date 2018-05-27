/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry;

import java.util.EventObject;

/**
 *
 * @author adrianohrl
 */
public class SessionEvent extends EventObject {
    
    private final String userName;
    private final String remainingDuration;
    
    /**
     * 
     * @param session
     * @param userName
     * @param remainingDuration 
     */
    public SessionEvent(Session session, String userName, String remainingDuration) {
        super(session);
        this.userName = userName;
        this.remainingDuration = remainingDuration;
    }
    
    /**
     *
     * @return
     */
    public Session getSession() {
        return (Session) super.getSource();
    }

    /**
     * 
     * @return 
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getRemainingDuration() {
        return remainingDuration;
    }
    
}
