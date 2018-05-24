/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DAO;
import tech.adrianohrl.identistry.model.individuals.CRO;

/**
 *
 * @author adrianohrl
 */
public class CRODAO extends DAO<CRO, String> {

    public CRODAO(EntityManager em) {
        super(em, CRO.class);
    }

    @Override
    public boolean isRegistered(CRO cro) {
        return super.find(cro.getRegistration()) != null;
    }
    
}
