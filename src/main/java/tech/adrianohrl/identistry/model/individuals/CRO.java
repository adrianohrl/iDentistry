/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import tech.adrianohrl.util.AddressUtil;

/**
 *
 * @author adrianohrl
 */
@Entity
public class CRO implements Comparable<CRO>, Serializable {
    
    @Id
    private String registration;
    @Column(nullable = false)
    private String uf;

    public CRO() {
    }

    public CRO(String registration, String uf) {
        this.registration = registration;
        this.uf = uf;
    }

    @Override
    public int compareTo(CRO cro) {
        return !uf.equalsIgnoreCase(cro.uf) ? uf.compareToIgnoreCase(cro.uf) :
                registration.compareToIgnoreCase(cro.registration);
    }

    @Override
    public String toString() {
        return AddressUtil.getUf(uf) + " " + registration;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CRO && equals((CRO) obj);
    }
    
    public boolean equals(CRO cro) {
        return cro != null && registration.equalsIgnoreCase(cro.registration) && uf.equalsIgnoreCase(cro.uf);
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
}
