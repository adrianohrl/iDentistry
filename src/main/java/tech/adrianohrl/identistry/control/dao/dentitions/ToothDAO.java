/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.dentitions;

import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DAO;
import tech.adrianohrl.identistry.model.dentitions.Tooth;

/**
 *
 * @author adrianohrl
 */
public class ToothDAO extends DAO<Tooth, Long> {

    public ToothDAO(EntityManager em) {
        super(em, Tooth.class);
    }

    @Override
    public boolean isRegistered(Tooth tooth) {
        return super.find(tooth.getCode()) != null;
    }
    
}
