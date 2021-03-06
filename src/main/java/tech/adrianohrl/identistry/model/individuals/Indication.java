/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author adrianohrl
 */
@Entity
public class Indication implements Comparable<Indication>, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long code;
    @ManyToOne(optional = false)
    private Channel channel;

    public Indication() {
    }

    public Indication(Channel channel) {
        this.channel = channel;
    }

    @Override
    public int compareTo(Indication indication) {
        return channel.compareTo(indication.channel);
    }

    @Override
    public String toString() {
        return channel.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Indication && equals((Indication) obj);
    }
    
    public boolean equals(Indication indication) {
        return indication != null && channel.equals(indication.channel);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    
}
