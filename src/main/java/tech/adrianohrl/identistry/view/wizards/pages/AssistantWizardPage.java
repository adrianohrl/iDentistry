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
import tech.adrianohrl.identistry.view.panels.AssistantPanel;
import tech.adrianohrl.identistry.view.wizards.NewWizardTypes;

/**
 *
 * @author adrianohrl
 */
public class AssistantWizardPage extends AbstractWizardPage {
    
    private static final Logger logger = Logger.getLogger(AssistantWizardPage.class);
    private final AbstractWizardPage nextPage = null;
    private final AssistantPanel panel;
    private final Assistant assistant;

    /**
     *
     * @param type
     * @param em
     * @param assistant
     */
    public AssistantWizardPage(NewWizardTypes type, EntityManager em, Assistant assistant) {
        this.assistant = assistant;
        this.panel = new AssistantPanel(this, em, assistant);
        logger.debug("Created new " + panel.getClass().getSimpleName() + ".");
        if (!type.isNewAssistant()) {
            throw new iDentistryException("Invalid wizard page request for AssistantWizardPage: " + type);
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
