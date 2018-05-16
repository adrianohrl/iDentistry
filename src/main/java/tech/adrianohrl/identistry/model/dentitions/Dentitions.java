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
public enum Dentitions {
    
    PERMANENT("Permanent", 1),
    DECIDUOUS("Deciduous", 5);
    
    private final String name;
    private final int numbering;

    private Dentitions(String name, int numbering) {
        this.name = name;
        this.numbering = numbering;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public static Dentitions get(int numbering) {
        return numbering > 50 ? DECIDUOUS : PERMANENT;
    }
    
    public int getNumbering() {
        return numbering;
    }
    
    public boolean isDeciduous() {
        return this == DECIDUOUS;
    }
    
    public static boolean isDeciduous(int numbering) {
        return numbering > 50;
    }
    
    public boolean isPermanent() {
        return this == PERMANENT;
    }
    
    public static boolean isPermanent(int numbering) {
        return numbering < 50;
    }
    
}
