/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.individuals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author adrianohrl
 */
public class Observation implements Iterable<Tag>, Serializable {
    
    private long code;
    private String obs;
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
