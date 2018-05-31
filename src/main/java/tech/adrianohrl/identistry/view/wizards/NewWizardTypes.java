/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.wizards;

/**
 *
 * @author adrianohrl
 */
public enum NewWizardTypes {
    
    NEW_PATIENT("New patient ..."),
    NEW_ASSISTANT("New assistant ..."),
    NEW_DENTIST("New dentist ...");
    
    private final String title;

    private NewWizardTypes(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
    public boolean isNewPatient() {
        return this == NEW_PATIENT;
    }
    
    public boolean isNewAssistant() {
        return this == NEW_ASSISTANT;
    }
    
    public boolean isNewDentist() {
        return this == NEW_DENTIST;
    }
}
