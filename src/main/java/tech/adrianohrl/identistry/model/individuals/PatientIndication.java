/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import javax.persistence.ManyToOne;

/**
 *
 * @author adrianohrl
 */
public class PatientIndication extends Indication {
    
    @ManyToOne(optional = false)
    private Patient patient;

    public PatientIndication() {
    }

    public PatientIndication(Patient patient, Channel channel) {
        super(channel);
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
}
