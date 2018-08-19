/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.wizards.pages;

import java.awt.FlowLayout;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import se.gustavkarlsson.gwiz.AbstractWizardPage;
import tech.adrianohrl.identistry.exceptions.iDentistryException;
import tech.adrianohrl.identistry.model.individuals.Dentist;
import tech.adrianohrl.identistry.view.panels.DentistPanel;

/**
 *
 * @author adrianohrl
 */
public class DentistWizardPanel extends AbstractWizardPage {
    
    private static final Logger logger = Logger.getLogger(DentistWizardPanel.class);
    private final AbstractWizardPage nextPage = null;
    private final DentistPanel panel;
    private final Dentist dentist;

    /**
     *
     * @param em
     * @param dentist
     */
    public DentistWizardPanel(EntityManager em, Dentist dentist) {
        this.panel = new DentistPanel(this, em, dentist);
        logger.debug("Created new " + panel.getClass().getSimpleName() + ".");
        if (dentist == null) {
            throw new iDentistryException("Invalid wizard page request for DentistWizardPage: the Dentist object must not be null.");
        }
        setLayout(new FlowLayout());
        add(panel);
        this.dentist = dentist;
    }

    @Override
    protected AbstractWizardPage getNextPage() {
        return nextPage;
    }

    @Override
    protected boolean isCancelAllowed() {
        return true;
    }

    @Override
    protected boolean isPreviousAllowed() {
        return true;
    }

    @Override
    protected boolean isNextAllowed() {
        return false;
    }

    @Override
    protected boolean isFinishAllowed() {
        return panel.isFilled();
    }
    
}
