/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import tech.adrianohrl.dao.DataSource;
import tech.adrianohrl.identistry.control.dao.individuals.LoggableDAO;
import tech.adrianohrl.identistry.model.individuals.Loggable;

/**
 *
 * @author adrianohrl
 */
public class Session {
    
    private final EntityManager em = DataSource.createEntityManager();
    private final LoggableDAO userDAO = new LoggableDAO(em);
    private Loggable loggedUser = null;
    private Calendar start = null;
    private double duration = 0.0;
    private Calendar deadline = null;
    
    public boolean isRegistered(String username) {
        return userDAO.isRegistered(username);
    }
    
    public boolean login(String username, char[] password) {
        Loggable user = userDAO.find(username);
        if (!user.authenticate(username, password)) {
            return false;
        }
        loggedUser = user;
        duration = user.getConfiguration().getSessionDuration();
        start = new GregorianCalendar();
        setDeadline();
        return true;
    }
    
    public boolean logout() {
        loggedUser = null;
        start = null;
        duration = 0.0;
        deadline = null;
        return true;
    }
    
    public boolean isLogged() {
        return loggedUser != null && start != null && !expired();
    }
    
    public boolean expired() {
        return deadline != null && deadline.before(new GregorianCalendar());
    }
    
    public boolean updateDeadline() {
        if (expired()) {
            return false;
        }
        setDeadline();
        return true;
    }
    
    private void setDeadline() {
        deadline = new GregorianCalendar();
        int hour = (int) (duration / 3600);
        deadline.add(Calendar.HOUR, hour);
        int minute = (int) ((duration - 3600 * hour) / 60);
        deadline.add(Calendar.MINUTE, minute);
        int second = (int) (duration - 3600 * hour - 60 * minute);
        deadline.add(Calendar.SECOND, second);
        int millisecond = (int) (1000 * (duration - Math.floor(duration)));
        deadline.add(Calendar.MILLISECOND, millisecond);
    }
    
    public void close() {
        em.close();
    }
    
}
