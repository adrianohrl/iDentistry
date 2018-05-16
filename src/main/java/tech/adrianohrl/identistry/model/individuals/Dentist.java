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
public class Dentist extends Loggable {
    
    private List<CRO> cros = new ArrayList<>();
    private List<Specialty> specialties = new ArrayList<>();

    public Dentist() {
    }

    public Dentist(String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(name, phone, dob, address, rg, cpf);
    }

    public Dentist(String username, String password, String email, String name, String phone, Calendar dob, Address address, String rg, String cpf) {
        super(username, password, email, name, phone, dob, address, rg, cpf);
    }

    public Dentist(String username, String password, String email, String name, String phone, Calendar dob, Address address, String rg, String cpf, List<CRO> cros, List<Specialty> specialties) {
        super(username, password, email, name, phone, dob, address, rg, cpf);
        this.cros = cros;
        this.specialties = specialties;
    }

    @Override
    public String toString() {
        return super.toString() + (!cros.isEmpty() ? " " + cros.get(0) : "");
    }
    
    public boolean hasSpecialty(Specialty specialty) {
        return specialties.contains(specialty);
    }
    
    public CRO getCro(String state) {
        for (CRO cro : cros) {
            if (state.equalsIgnoreCase(cro.getState())) {
                return cro;
            }
        }
        return null;
    }

    public List<CRO> getCros() {
        return cros;
    }

    public void setCros(List<CRO> cros) {
        this.cros = cros;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }
    
}
