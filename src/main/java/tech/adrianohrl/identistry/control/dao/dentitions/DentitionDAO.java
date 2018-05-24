/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.dentitions;

import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DAO;
import tech.adrianohrl.identistry.model.dentitions.Dentition;

/**
 *
 * @author adrianohrl
 */
public class DentitionDAO extends DAO<Dentition, Long> {

    public DentitionDAO(EntityManager em) {
        super(em, Dentition.class);
    }

    @Override
    public boolean isRegistered(Dentition dentition) {
        return super.find(dentition.getCode()) != null;
    }
    
}
