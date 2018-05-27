/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author adrianohrl
 */
public class DataSource {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("iDentistryPU");

    private DataSource() {
    }

    public static EntityManager createEntityManager() throws DataSourceException {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
        } catch (PersistenceException e) {
            emf.close();
            throw new DataSourceException("The database server must be initialized firstly in order to use the application properly.");
        }
        return em;
    }
    
    public static void closeEntityManagerFactory() {
        emf.close();
    }
    
}
