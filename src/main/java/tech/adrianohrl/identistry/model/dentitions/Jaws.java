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
public enum Jaws {
    
    MAXILLARY("Maxillary", 0),
    MANDIBULAR("Mandibular", 2);
    
    private final String name;
    private final int numbering;

    private Jaws(String name, int numbering) {
        this.name = name;
        this.numbering = numbering;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public static Jaws get(int numbering) {
        return numbering < 30 || numbering > 50 && numbering < 70 ? MAXILLARY : MANDIBULAR; 
    }

    public int getNumbering() {
        return numbering;
    }
    
    public boolean isMaxillary() {
        return this == MAXILLARY;
    }
    
    public static boolean isMaxillary(int numbering) {
        return numbering < 30 || numbering > 50 && numbering < 70;
    }
    
    public boolean isMandibular() {
        return this == MANDIBULAR;
    }
    
    public static boolean isMandibular(int numbering) {
        return numbering > 30 && numbering < 50 || numbering > 70;
    }
    
}
