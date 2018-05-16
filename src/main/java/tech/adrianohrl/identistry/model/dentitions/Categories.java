/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.dentitions;

import tech.adrianohrl.identistry.exceptions.iDentistryException;

/**
 *
 * @author adrianohrl
 */
public enum Categories {
    
    CENTRAL_INCISOR("Central Incisor", 1, 1),
    LATERAL_INCISOR("Lateral Incisor", 2, 2),
    CANINE("Canine", 3, 3),
    FIRST_PREMOLAR("First Premolar", 4),
    SECOND_PREMOLAR("Second Premolar", 5),
    FIRST_MOLAR("First Molar", 4, 6),
    SECOND_MOLAR("Second Molar", 5, 7),
    THIRD_MOLAR("Third Molar", 8);
    
    private final String name;
    private final int deciduousNumbering;
    private final int permanentNumbering;

    private Categories(String name, int permanentNumbering) {
        this(name, -1, permanentNumbering);
    }

    private Categories(String name, int deciduousNumbering, int permanentNumbering) {
        this.name = name;
        this.deciduousNumbering = deciduousNumbering;
        this.permanentNumbering = permanentNumbering;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public int getNumbering(Dentitions dentition) {
        if (dentition.isDeciduous()) {
            if (deciduousNumbering == -1) {
                throw new iDentistryException("The deciduous numbering is undefined.");
            }
            return deciduousNumbering;
        }
        return permanentNumbering;
    }
    
    public static Categories get(int numbering) {
        Dentitions dentitions = Dentitions.get(numbering);
        if (dentitions.isDeciduous()) {
            switch (numbering % 10) {
                case 1:
                    return CENTRAL_INCISOR;
                case 2:
                    return LATERAL_INCISOR;
                case 3:
                    return CANINE;
                case 4:
                    return FIRST_MOLAR;
                case 5:
                    return SECOND_MOLAR;
                default:
                    throw new iDentistryException("This number does not exist.");
            }
        } else {
            switch (numbering % 10) {
                case 1:
                    return CENTRAL_INCISOR;
                case 2:
                    return LATERAL_INCISOR;
                case 3:
                    return CANINE;
                case 4:
                    return FIRST_PREMOLAR;
                case 5:
                    return SECOND_PREMOLAR;
                case 6:
                    return FIRST_MOLAR;
                case 7:
                    return SECOND_MOLAR;
                case 8:
                    return THIRD_MOLAR;
                default:
                    throw new iDentistryException("This number does not exist.");
            }
        }
    }
    
    public boolean isCentralIncisor() {
        return this == CENTRAL_INCISOR;
    }
    
    public static boolean isCentralIncisor(int numbering) {
        return numbering % 10 == 1;
    }
    
    public boolean isLateralIncisor() {
        return this == LATERAL_INCISOR;
    }
    
    public static boolean isLateralIncisor(int numbering) {
        return numbering % 10 == 2;
    }
    
    public boolean isCanine() {
        return this == CANINE;
    }
    
    public static boolean isCanine(int numbering) {
        return numbering % 10 == 3;
    }
    
    public boolean isFirstPremolar() {
        return this == FIRST_PREMOLAR;
    }
    
    public static boolean isFirstPremolar(int numbering) {
        return Dentitions.isPermanent(numbering) && numbering % 10 == 4;
    }
    
    public boolean isSecondPremolar() {
        return this == SECOND_PREMOLAR;
    }
    
    public static boolean isSecondPremolar(int numbering) {
        return Dentitions.isPermanent(numbering) && numbering % 10 == 5;
    }    
    
    public boolean isFirstMolar() {
        return this == FIRST_MOLAR;
    }
    
    public static boolean isFirstMolar(int numbering) {
        return Dentitions.isDeciduous(numbering) && numbering % 10 == 4 
                || Dentitions.isPermanent(numbering) && numbering % 10 == 6;
    }
    
    public boolean isSecondMolar() {
        return this == SECOND_MOLAR;
    }
    
    public static boolean isSecondMolar(int numbering) {
        return Dentitions.isDeciduous(numbering) && numbering % 10 == 5 
                || Dentitions.isPermanent(numbering) && numbering % 10 == 7;
    }
    
    public boolean isThirdMolar() {
        return this == THIRD_MOLAR;
    }
    
    public static boolean isThirdMolar(int numbering) {
        return Dentitions.isPermanent(numbering) && numbering % 10 == 8;
    }
    
}
