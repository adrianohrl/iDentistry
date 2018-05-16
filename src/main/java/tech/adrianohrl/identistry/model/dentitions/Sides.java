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
public enum Sides {
    
    RIGHT("Right", 0),
    LEFT("Left", 1);
    
    private final String name;
    private final int numbering;

    private Sides(String name, int numbering) {
        this.name = name;
        this.numbering = numbering;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getNumbering() {
        return numbering;
    }
    
    public static Sides get(int numbering) {
        return Math.round(numbering / 20) % 2 == 0 ? RIGHT : LEFT;
    }
    
    public boolean isRight() {
        return this == RIGHT;
    }
    
    public static boolean isRight(int numbering) {
        return Math.round(numbering / 20) % 2 == 0;
    }
    
    public boolean isLeft() {
        return this == LEFT;
    }
    
    public static boolean isLeft(int numbering) {
        return Math.round(numbering / 20) % 2 != 0;
    }
    
}
