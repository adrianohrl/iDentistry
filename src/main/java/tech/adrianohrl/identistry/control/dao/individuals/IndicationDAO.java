/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DAO;
import tech.adrianohrl.identistry.model.individuals.Indication;

/**
 *
 * @author adrianohrl
 * @param <I>
 */
public class IndicationDAO<I extends Indication> extends DAO<I, Long> {
    
    public IndicationDAO(EntityManager em) {
        super(em, Indication.class);
    }

    protected IndicationDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }

    @Override
    public boolean isRegistered(I indication) {
        return super.find(indication.getCode()) != null;
    }
    
}
