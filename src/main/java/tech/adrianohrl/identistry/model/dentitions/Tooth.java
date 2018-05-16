/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.dentitions;

import java.io.Serializable;

/**
 *
 * @author adrianohrl
 */
public class Tooth implements Comparable<Tooth>, Serializable {
    
    private long code;
    private int numbering;
    private Dentitions dentition;
    private Sides side;
    private Jaws jaw;
    private Categories category;
    private boolean erupted = true;
    private boolean extracted = false;

    public Tooth() {
    }
    
    public Tooth(int numbering, boolean erupted, boolean extracted) {
        this.numbering = numbering;
        this.erupted = erupted;
        this.extracted = extracted;
        this.dentition = Dentitions.get(numbering);
        this.side = Sides.get(numbering);
        this.jaw = Jaws.get(numbering);
        this.category = Categories.get(numbering);
    }

    public Tooth(Dentitions dentition, Sides side, Jaws jaw, Categories category, boolean erupted, boolean extracted) {
        this.dentition = dentition;
        this.side = side;
        this.jaw = jaw;
        this.category = category;
        this.erupted = erupted;
        this.extracted = extracted;
        this.numbering = 10 * (dentition.getNumbering() + side.getNumbering() + jaw.getNumbering())
                + category.getNumbering(dentition);
    }

    @Override
    public int compareTo(Tooth tooth) {
        return numbering - tooth.numbering;
    }

    @Override
    public String toString() {
        return dentition + " " + side + " " + jaw + " " + category + " (" + numbering + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tooth && equals((Tooth) obj) || obj instanceof Integer && equals((Integer) obj);
    }
    
    public boolean equals(Tooth tooth) {
        return tooth != null && numbering == tooth.numbering;
    }
    
    public boolean equals(Integer numbering) {
        return numbering != null && this.numbering == numbering;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public int getNumbering() {
        return numbering;
    }

    public void setNumbering(int numbering) {
        this.numbering = numbering;
    }

    public Dentitions getDentition() {
        return dentition;
    }

    public void setDentition(Dentitions dentition) {
        this.dentition = dentition;
    }

    public Sides getSide() {
        return side;
    }

    public void setSide(Sides side) {
        this.side = side;
    }

    public Jaws getJaw() {
        return jaw;
    }

    public void setJaws(Jaws jaw) {
        this.jaw = jaw;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public boolean isErupted() {
        return erupted;
    }

    public void setErupted(boolean erupted) {
        this.erupted = erupted;
    }

    public boolean isExtracted() {
        return extracted;
    }

    public void setExtracted(boolean extracted) {
        this.extracted = extracted;
    }
    
}
