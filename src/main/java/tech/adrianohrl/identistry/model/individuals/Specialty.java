/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Specialty implements Comparable<Specialty>, Serializable {
    
    @Id
    private String name;
    private String obs;

    public Specialty() {
    }

    public Specialty(String name, String obs) {
        this.name = name;
        this.obs = obs;
    }

    @Override
    public int compareTo(Specialty specialty) {
        return name.compareToIgnoreCase(specialty.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Specialty && equals((Specialty) obj);
    }
    
    public boolean equals(Specialty specialty) {
        return specialty != null && name.equalsIgnoreCase(specialty.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
}
