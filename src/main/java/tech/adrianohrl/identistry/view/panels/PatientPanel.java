/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.panels;

import java.awt.Component;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import se.gustavkarlsson.gwiz.AbstractWizardPage;
import tech.adrianohrl.identistry.control.dao.individuals.PatientDAO;
import tech.adrianohrl.identistry.model.individuals.Patient;

/**
 *
 * @author adrianohrl
 */
public class PatientPanel extends AbstractWizardPagePanel {
    
    private static final Logger logger = Logger.getLogger(PatientPanel.class);
    private final PatientDAO dao;
    private final Patient patient;

    /**
     * Creates new form PatientPanel
     * @param parent
     * @param em
     * @param patient
     */
    public PatientPanel(AbstractWizardPage parent, EntityManager em, Patient patient) {
        super(parent, em);
        this.dao = new PatientDAO(em);
        this.patient = patient;
        initComponents();
        setMandatoryFieldsListeners();
        fill();
    }

    @Override
    public boolean isFilled() {
        return false;
    }

    @Override
    public Component getFirstFocusableComponent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLastFocusableComponent(Component component) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setMandatoryFieldsListeners() {
        
    }
    
    private void fill() {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}