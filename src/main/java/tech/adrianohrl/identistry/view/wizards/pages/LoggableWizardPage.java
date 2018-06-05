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
import tech.adrianohrl.identistry.model.individuals.Assistant;
import tech.adrianohrl.identistry.model.individuals.Dentist;
import tech.adrianohrl.identistry.model.individuals.Loggable;
import tech.adrianohrl.identistry.view.panels.LoggablePanel;
import tech.adrianohrl.identistry.view.wizards.NewWizardTypes;

/**
 *
 * @author adrianohrl
 */
public class LoggableWizardPage extends AbstractWizardPage {
    
    private static final Logger logger = Logger.getLogger(LoggableWizardPage.class);
    private final AbstractWizardPage nextPage;
    private final LoggablePanel panel;
    private final NewWizardTypes type;
    private final Loggable loggable;

    /**
     *
     * @param type
     * @param em
     * @param loggable
     */
    public LoggableWizardPage(NewWizardTypes type, EntityManager em, Loggable loggable) {
        this.type = type;
        this.loggable = loggable;
        this.panel = new LoggablePanel(this, em, loggable);
        logger.debug("Created new " + panel.getClass().getSimpleName() + ".");
        switch (type) {
            case NEW_ASSISTANT:
                if (!(loggable instanceof Assistant)) {
                    throw new iDentistryException("The given loggable object must be an instance of the Assistant class in order to build an Assistant object.");
                }
                //nextPage = new AssistantWizardPage(type, em, (Assistant) loggable);
                nextPage = null;
                break;
            case NEW_DENTIST:
                if (!(loggable instanceof Dentist)) {
                    throw new iDentistryException("The given loggable object must be an instance of the Dentist class in order to build a Dentist object.");
                }
                nextPage = new DentistWizardPanel(type, em, (Dentist) loggable);
                break;
            default:
                throw new iDentistryException("Invalid wizard page request for LoggableWizardPage: " + type);
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
        return type.isNewDentist() && panel.isFilled();
    }

    @Override
    protected boolean isFinishAllowed() {
        return type.isNewAssistant() && panel.isFilled();
    }
    
}
