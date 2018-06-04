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
import tech.adrianohrl.identistry.view.panels.PersonPanel;
import tech.adrianohrl.identistry.view.wizards.NewWizardTypes;

/**
 *
 * @author adrianohrl
 */
public class PersonWizardPage extends AbstractWizardPage {
    
    private static final Logger logger = Logger.getLogger(PersonWizardPage.class);
    private final AbstractWizardPage nextPage;
    private final PersonPanel panel;

    public PersonWizardPage(NewWizardTypes type, EntityManager em) {
        panel = new PersonPanel(this, em);
        logger.debug("Created new " + panel.getClass().getSimpleName() + ".");
        switch (type) {
            case NEW_PATIENT:
                nextPage = new PatientWizardPage(type, em);
                break;
            case NEW_ASSISTANT:
            case NEW_DENTIST:
                nextPage = new LoggableWizardPage(type, em);
                break;
            default:
                throw new iDentistryException("Invalid wizard page request for PersonWizardPage: " + type);
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
        return false;
    }

    @Override
    protected boolean isNextAllowed() {
        return panel.isFilled();
    }

    @Override
    protected boolean isFinishAllowed() {
        return false;
    }
    
}
