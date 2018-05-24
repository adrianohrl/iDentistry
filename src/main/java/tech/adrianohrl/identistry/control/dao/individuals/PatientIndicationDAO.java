/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import tech.adrianohrl.identistry.model.individuals.PatientIndication;

/**
 *
 * @author adrianohrl
 */
public class PatientIndicationDAO extends IndicationDAO<PatientIndication> {

    public PatientIndicationDAO(EntityManager em) {
        super(em, PatientIndication.class);
    }
    
}
