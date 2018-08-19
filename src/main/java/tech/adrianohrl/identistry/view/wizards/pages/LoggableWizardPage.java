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

/**
 *
 * @author adrianohrl
 */
public class LoggableWizardPage extends AbstractWizardPage {
    
    private static final Logger logger = Logger.getLogger(LoggableWizardPage.class);
    private final AbstractWizardPage nextPage;
    private final LoggablePanel panel;
    private final Loggable loggable;

    /**
     *
     * @param em
     * @param loggable
     */
    public LoggableWizardPage(EntityManager em, Loggable loggable) {
        this.loggable = loggable;
        this.panel = new LoggablePanel(this, em, loggable);
        logger.debug("Created new " + panel.getClass().getSimpleName() + ".");
        String type = loggable.getClass().getSimpleName();
        switch (type) {
            case "Assistant":
                //nextPage = new AssistantWizardPage(em, (Assistant) loggable);
                nextPage = null;
                break;
            case "Dentist":
                nextPage = new DentistWizardPanel(em, (Dentist) loggable);
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
        return loggable instanceof Dentist && panel.isFilled();
    }

    @Override
    protected boolean isFinishAllowed() {
        return loggable instanceof Assistant && panel.isFilled();
    }
    
}
