/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DAO;
import tech.adrianohrl.identistry.model.individuals.Tag;

/**
 *
 * @author adrianohrl
 */
public class TagDAO extends DAO<Tag, String> {

    public TagDAO(EntityManager em) {
        super(em, Tag.class);
    }

    @Override
    public boolean isRegistered(Tag tag) {
        return super.find(tag.getName()) != null;
    }
    
}
