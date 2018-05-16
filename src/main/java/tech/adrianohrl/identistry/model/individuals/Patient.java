/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author adrianohrl
 */
public class Patient extends Person {
    
    private Anamnesis anamnesis = new Anamnesis();
    private List<Observation> obss = new ArrayList<>();
    private Person responsible;
    private Indication indication;

    public Patient() {
    }

    public Patient(String name, String phone) {
        super(name, phone);
    }

    public Patient(String name, String phone, Calendar dob, Address address) {
        super(name, phone, dob, address);
    }

    public Patient(String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(name, phone, dob, address, rg, cpf);
    }

    public Patient(Person responsible, Indication indication, String name, String phone, Calendar dob, Address address) {
        super(name, phone, dob, address);
        if (isUnderage() && responsible == null) {
            throw new RuntimeException("Underage person must have a responsible person.");
        }
        this.responsible = responsible;
        this.indication = indication;
    }

    public Patient(Person responsible, Indication indication, String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(name, phone, dob, address, rg, cpf);
        if (isUnderage() && responsible == null) {
            throw new RuntimeException("Underage person must have a responsible person.");
        }
        this.responsible = responsible;
        this.indication = indication;
    }

    public Anamnesis getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(Anamnesis anamnesis) {
        this.anamnesis = anamnesis;
    }

    public List<Observation> getObss() {
        return obss;
    }

    public void setObss(List<Observation> obss) {
        this.obss = obss;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public Indication getIndication() {
        return indication;
    }

    public void setIndication(Indication indication) {
        this.indication = indication;
    }
    
}
