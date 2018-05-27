/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry;

import java.util.EventListener;

/**
 *
 * @author adrianohrl
 */
public interface SessionEventListener extends EventListener {
    
    /**
     *
     * @param evt
     */
    public abstract void userLoggedIn(SessionEvent evt);
    
    /**
     *
     * @param evt
     */
    public abstract void updateRemainingDuration(SessionEvent evt);
    
    /**
     *
     * @param evt
     */
    public abstract void userLoggedOut(SessionEvent evt);
    
}
