/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.events;

import java.util.EventObject;
import tech.adrianohrl.identistry.exceptions.iDentistryException;
import tech.adrianohrl.identistry.model.individuals.Loggable;

/**
 *
 * @author adrianohrl
 */
public class UserLoginEvent extends EventObject {

    /**
     *
     * @param loggable
     */
    public UserLoginEvent(Loggable loggable) {
        super(loggable);
        if (loggable == null) {
            throw new iDentistryException("The loggable user must not be null in the login event.");
        }
    }
    
    /**
     *
     * @return
     */
    public Loggable getUser() {
        return (Loggable) super.getSource();
    }
    
}
