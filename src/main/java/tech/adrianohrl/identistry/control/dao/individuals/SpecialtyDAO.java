/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DAO;
import tech.adrianohrl.identistry.model.individuals.Specialty;

/**
 *
 * @author adrianohrl
 */
public class SpecialtyDAO extends DAO<Specialty, String> {

    public SpecialtyDAO(EntityManager em) {
        super(em, Specialty.class);
    }

    @Override
    public boolean isRegistered(Specialty specialty) {
        return super.find(specialty.getName()) != null;
    }
    
}
