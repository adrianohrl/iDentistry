/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.dentitions;

/**
 *
 * @author adrianohrl
 */
public enum Stages {
    
    DECIDUOUS("Deciduous Dentition"),
    MIXED("Mixed Dentition"),
    PERMANENT("Permanent Dentition");
    
    private final String name;

    private Stages(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public boolean isDeciduous() {
        return this == DECIDUOUS;
    }
    
    public boolean isMixed() {
        return this == MIXED;
    }
    
    public boolean isPermanent() {
        return this == PERMANENT;
    }
    
}
