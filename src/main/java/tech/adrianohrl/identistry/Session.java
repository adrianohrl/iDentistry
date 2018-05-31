/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.swing.Timer;
import javax.swing.event.EventListenerList;
import org.apache.log4j.Logger;
import tech.adrianohrl.dao.DataSource;
import tech.adrianohrl.dao.DataSourceException;
import tech.adrianohrl.identistry.control.dao.individuals.LoggableDAO;
import tech.adrianohrl.identistry.model.individuals.Loggable;
import tech.adrianohrl.util.CalendarFormat;

/**
 *
 * @author adrianohrl
 */
public class Session implements ActionListener {
    
    private final EntityManager em = DataSource.createEntityManager();;
    private final LoggableDAO userDAO = new LoggableDAO(em);
    private Loggable loggedUser = null;
    private Calendar start = null;
    private double duration = 0.0;
    private Calendar deadline = null;
    private Timer timer = new Timer(1000, this);;
    private static final Logger logger = Logger.getLogger(Session.class);
    protected EventListenerList listeners = new EventListenerList();

    public Session() throws DataSourceException {
    }
    
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
        timer.start();
        logger.info(username + " is logged in the iDentistry application.");
        notifyLogin();
        return true;
    }
    
    public boolean updateDeadline() {
        if (isExpired()) {
            logger.error(loggedUser.getUsername() + "'s session became expired.");
            return false;
        }
        setDeadline();
        return true;
    }
    
    public boolean logout() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        Loggable user = loggedUser;
        loggedUser = null;
        start = null;
        duration = 0.0;
        deadline = null;
        if (user == null) {
            logger.warn("Unneeded logout.");
            return false;
        }
        logger.info(user.getUsername() + " is logged out the iDentistry application.");
        notifyLogout(user.getName());
        return true;
    }
    
    public boolean isLogged() {
        return loggedUser != null && start != null && !isExpired();
    }
    
    public boolean isExpired() {
        return duration >= 0.0 && deadline != null && deadline.before(new GregorianCalendar());
    }
    
    public String getRemainingSessionDuration() {
        if (duration <= 0.0 || deadline == null) {
            return "-- : -- : --";
        }
        Calendar now = new GregorianCalendar();
        double diff = ((double) (deadline.getTimeInMillis() - now.getTimeInMillis())) / 1000.0;
        int hour = (int) (diff / 3600);
        int minute = (int) ((diff - 3600 * hour) / 60);
        int second =  (int) (diff - 3600 * hour - 60 * minute);
        return String.format("%02d", hour) + " : " + String.format("%02d", minute) + " : " + String.format("%02d", second);
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
        logger.debug("Updated " + loggedUser.getUsername() + "'s session duration to " + CalendarFormat.format(deadline) + ".");
    }
    
    public void close() {
        if (isLogged()) {
            logout();
        }
        em.close();
        DataSource.closeEntityManagerFactory();
        logger.debug("Closed session.");
    }
    
    public void addSessionEventListener(SessionEventListener listener) {
        System.out.println("Adding listener to Session ... " + listener);
        listeners.add(SessionEventListener.class, listener);
    }
    
    public void removeSessionListener(SessionEventListener listener) {
        System.out.println("Removing listener to Session ... " + listener);
        listeners.remove(SessionEventListener.class, listener);
    }
    
    private void notifyLogin() {
        Object[] vector = listeners.getListenerList();
        SessionEvent evt = new SessionEvent(this, loggedUser.getName(), getRemainingSessionDuration());
        for (int i = 0; i < vector.length; i += 2) {
            if (vector[i] == SessionEventListener.class) {
                SessionEventListener listener = (SessionEventListener) vector[i + 1];
                listener.userLoggedIn(evt);
            }
        }
    }
    
    private void notifyRemainingDuration() {
        Object[] vector = listeners.getListenerList();
        SessionEvent evt = new SessionEvent(this, loggedUser.getName(), getRemainingSessionDuration());
        for (int i = 0; i < vector.length; i += 2) {
            if (vector[i] == SessionEventListener.class) {
                SessionEventListener listener = (SessionEventListener) vector[i + 1];
                listener.updateRemainingDuration(evt);
            }
        }
    }
    
    private void notifyLogout(String userName) {
        Object[] vector = listeners.getListenerList();
        SessionEvent evt = new SessionEvent(this, userName, getRemainingSessionDuration());
        for (int i = 0; i < vector.length; i += 2) {
            if (vector[i] == SessionEventListener.class) {
                SessionEventListener listener = (SessionEventListener) vector[i + 1];
                listener.userLoggedOut(evt);
            }
        }
    }

    public Loggable getLoggedUser() {
        return loggedUser;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (isExpired()) {
            logger.error("The " + loggedUser.getUsername() + "'s session became expired.");
            logout();
            return;
        }
        notifyRemainingDuration();
    }
    
    /*private class SessionExpirationTimer extends TimerTask {

        @Override
        public void run() {
            if (!isExpired()) {
                Session.this.setDeadline();
                return;
            } 
            Session.logger.error("The " + loggedUser.getUsername() + "'s session became expired.");
            Session.this.logout();
        }
        
    }*/
    
    public static void main(String[] args) throws InterruptedException, DataSourceException {
        Session session = new Session();
        session.duration = 75.0;
        session.deadline = new GregorianCalendar();
        session.deadline.add(Calendar.HOUR, 1);
        session.deadline.add(Calendar.MINUTE, 0);
        session.deadline.add(Calendar.SECOND, 5);
        while (!session.isExpired()) {
            System.out.println("Remaing session duration: " + session.getRemainingSessionDuration());
            Thread.sleep(1000);
        }
        System.out.println("Session expired.");
    }
    
}
