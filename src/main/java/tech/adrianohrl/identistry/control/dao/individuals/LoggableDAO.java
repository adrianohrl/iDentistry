/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.control.dao.individuals;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import tech.adrianohrl.identistry.model.individuals.Loggable;

/**
 *
 * @author adrianohrl
 * @param <L>
 */
public class LoggableDAO<L extends Loggable> extends PersonDAO<L> {
    
    public LoggableDAO(EntityManager em) {
        super(em, Loggable.class);
    }

    protected LoggableDAO(EntityManager em, Class clazz) {
        super(em, clazz);
    }

    @Override
    public L find(String username) {
        L loggable = super.find(username);
        if (loggable == null) {
            try {
                loggable = (L) em.createQuery("SELECT l "
                    + "FROM Loggable l "
                    + "WHERE l.username = '" + username + "'").getSingleResult();
            } catch (NoResultException e) {
                loggable = null;
            }
        }
        return loggable;
    }
    
    @Override
    public boolean isRegistered(String username) {
        if (super.isRegistered(username)) {
            return true;
        }
        long counter = (long) em.createQuery("SELECT COUNT(*) "
                + "FROM Loggable l "
                + "WHERE l.username = '" + username + "'").getSingleResult();
        return counter > 0;
    }
    
}
