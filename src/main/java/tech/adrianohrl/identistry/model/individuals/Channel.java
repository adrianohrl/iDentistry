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
public class Channel implements Comparable<Channel>, Serializable {
    
    private String name;

    public Channel() {
    }

    public Channel(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Channel channel) {
        return name.compareToIgnoreCase(channel.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Channel && equals((Channel) obj);
    }
    
    public boolean equals(Channel channel) {
        return channel != null && name.equalsIgnoreCase(channel.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
