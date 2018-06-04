/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.panels;

import javax.persistence.EntityManager;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import se.gustavkarlsson.gwiz.AbstractWizardPage;

/**
 *
 * @author adrianohrl
 */
public abstract class AbstractWizardPagePanel extends javax.swing.JPanel implements WizardPagePanel {
    
    protected final EntityManager em;
    protected final TextFieldChangeListener listener = new TextFieldChangeListener();
    protected final AbstractWizardPage parent;

    public AbstractWizardPagePanel(AbstractWizardPage parent, EntityManager em) {
        this.parent = parent;
        this.em = em;
    }
    
    protected class TextFieldChangeListener implements DocumentListener {
        
        public void assignToListenerList(JTextField field) {
            field.getDocument().addDocumentListener(listener);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            parent.updateWizardButtons();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            parent.updateWizardButtons();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            parent.updateWizardButtons();
        }
        
    }
    
    protected abstract void setMandatoryFieldsListeners();
    
}
