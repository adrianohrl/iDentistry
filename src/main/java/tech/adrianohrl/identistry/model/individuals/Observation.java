/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Observation implements Iterable<Tag>, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    private String obs;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "Observation_Tag", 
        joinColumns = @JoinColumn(name = "observation_code"),
        inverseJoinColumns = @JoinColumn(name = "tag_name"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"observation_code", "tag_name"})
    )
    private List<Tag> tags = new ArrayList<>();    

    public Observation() {
    }

    public Observation(String obs, List<Tag> tags) {
        this.obs = obs;
        this.tags = tags;
    }

    @Override
    public Iterator<Tag> iterator() {
        return tags.iterator();
    }

    @Override
    public String toString() {
        String aux = "Tags:";
        for (Tag tag : tags) {
            aux += " " + tag + ".";
        }
        return obs + aux;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Observation && equals((Observation) obj);
    }
    
    public boolean equals(Observation observation) {
        if (observation == null) {
            return false;
        }
        for (Tag tag : observation) {
            if (!tags.contains(tag)) {
                return false;
            }
        }
        return false;
    }
    
    public boolean hasTag(Tag tag) {
        for (Tag t : this) {
            if (t.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    
}
