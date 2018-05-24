/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DAO;
import tech.adrianohrl.identistry.model.individuals.Person;

/**
 *
 * @author adrianohrl
 * @param <P>
 */
public class PersonDAO<P extends Person> extends DAO<P, String> {

    public PersonDAO(EntityManager em) {
        super(em, Person.class);
    }
    
    protected PersonDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }

    @Override
    public boolean isRegistered(P person) {
        return super.find(person.getName()) != null;
    }
    
    public boolean isRegistered(String name) {
        long counter = (long) em.createQuery("SELECT COUNT(*) "
                + "FROM Person p "
                + "WHERE p.name = '" + name + "'").getSingleResult();
        return counter > 0;
    }
    
}
