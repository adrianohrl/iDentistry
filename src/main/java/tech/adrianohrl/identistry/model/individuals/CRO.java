/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.Serializable;

/**
 *
 * @author adrianohrl
 */
public class CRO implements Comparable<CRO>, Serializable {
    
    private String registration;
    private String state;

    public CRO() {
    }

    public CRO(String registration, String state) {
        this.registration = registration;
        this.state = state;
    }

    @Override
    public int compareTo(CRO cro) {
        return !state.equalsIgnoreCase(cro.state) ? state.compareToIgnoreCase(cro.state) :
                registration.compareToIgnoreCase(cro.registration);
    }

    @Override
    public String toString() {
        return "CRO-" + state + " " + registration;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CRO && equals((CRO) obj);
    }
    
    public boolean equals(CRO cro) {
        return cro != null && registration.equalsIgnoreCase(cro.registration) && state.equalsIgnoreCase(cro.state);
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
}
