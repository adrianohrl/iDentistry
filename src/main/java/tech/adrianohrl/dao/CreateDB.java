/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.dao;

import javax.persistence.EntityManager;

/**
 *
 * @author adrianohrl
 */
public class CreateDB {

    public static void main(String[] args) throws DataSourceException {
        EntityManager em = DataSource.createEntityManager();
        em.close();
        DataSource.closeEntityManagerFactory();
    }
    
}
