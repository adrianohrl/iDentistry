/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import tech.adrianohrl.identistry.model.individuals.Dentist;

/**
 *
 * @author adrianohrl
 */
public class DentistDAO extends LoggableDAO<Dentist> {

    public DentistDAO(EntityManager em) {
        super(em, Dentist.class);
    }
    
}
