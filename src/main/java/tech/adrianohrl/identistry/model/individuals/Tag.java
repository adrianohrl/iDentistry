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
public class Tag implements Comparable<Tag>, Serializable {
    
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Tag tag) {
        return name.compareToIgnoreCase(tag.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tag && equals((Tag) obj);
    }
    
    public boolean equals(Tag tag) {
        return tag != null && name.equalsIgnoreCase(tag.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
