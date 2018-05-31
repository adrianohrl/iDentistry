/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.panels;

import java.awt.Component;

/**
 *
 * @author adrianohrl
 */
public interface WizardPagePanel {
    
    /**
     * Check if all required input fields are filled properly.
     * 
     * @return true if the whole panel is filled out, otherwise false.
     */
    public abstract boolean isFilled();
    
    /**
     * This method should be implemented as returning the panel first focusable component. 
     * It helps to properly set the Tab key chain.
     * 
     * @return the first focusable component of the panel.
     */
    public abstract Component getFirstFocusableComponent();
    
    /**
     * This method should be implemented as setting the panel last focusable component.
     * It helps to properly set the Tab key chain.
     * 
     * @param component the last focusable component of the panel. Normally it is the 
     * wizard's default button.
     */
    public abstract void setLastFocusableComponent(Component component);
    
}
