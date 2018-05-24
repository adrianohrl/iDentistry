/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.events;

import java.util.EventListener;

/**
 *
 * @author adrianohrl
 */
public interface UserLoginEventListener extends EventListener {
    
    /**
     *
     * @param event
     */
    public abstract void userLoggedIn(UserLoginEvent event);
    
    /**
     *
     * @param event
     */
    public abstract void userLoggedOut(UserLoginEvent event);
    
}
