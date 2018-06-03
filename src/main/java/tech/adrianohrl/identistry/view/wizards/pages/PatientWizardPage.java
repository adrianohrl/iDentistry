/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.wizards.pages;

import java.awt.FlowLayout;
import org.apache.log4j.Logger;
import se.gustavkarlsson.gwiz.AbstractWizardPage;
import tech.adrianohrl.identistry.exceptions.iDentistryException;
import tech.adrianohrl.identistry.view.panels.PatientPanel;
import tech.adrianohrl.identistry.view.wizards.NewWizardTypes;

/**
 *
 * @author adrianohrl
 */
public class PatientWizardPage extends AbstractWizardPage {
    
    private static final Logger logger = Logger.getLogger(PatientWizardPage.class);
    private final AbstractWizardPage nextPage = null;
    private final PatientPanel panel = new PatientPanel(this);

    public PatientWizardPage(NewWizardTypes type) {
        logger.debug("Created new " + panel.getClass().getSimpleName() + ".");
        if (!type.isNewPatient()) {
            throw new iDentistryException("Invalid wizard page request for PatientWizardPage: " + type);
        }
        setLayout(new FlowLayout());
        add(panel);
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
