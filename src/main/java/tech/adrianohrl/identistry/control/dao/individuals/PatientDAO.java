/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import tech.adrianohrl.identistry.model.individuals.Patient;

/**
 *
 * @author adrianohrl
 */
public class PatientDAO extends PersonDAO<Patient> {

    public PatientDAO(EntityManager em) {
        super(em, Patient.class);
    }

    @Override
    public void create(Patient patient) throws EntityExistsException {
        AddressDAO addressDAO = new AddressDAO(em);
        addressDAO.create(patient.getAddress());
        super.create(patient);
    }
    
}
