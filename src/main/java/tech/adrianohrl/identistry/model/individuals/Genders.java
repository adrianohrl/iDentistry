/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

/**
 *
 * @author adrianohrl
 */
public enum Genders {
    
    MALE("Male"),
    FEMALE("Female");
    
    private final String name;
    
    private Genders(String name) {
        this.name = name;
    }
    
    public static Genders getInstance(String name) {
        if (name != null) {
            if (name.equalsIgnoreCase("Female") || name.equalsIgnoreCase("Fem.") || name.equalsIgnoreCase("Feminino")) {
                return FEMALE;
            } else if (name.equalsIgnoreCase("Male") || name.equalsIgnoreCase("Masc.") || name.equalsIgnoreCase("Masculino")) {
                return MALE;
            }
        }
        return null;
    }
    
}
