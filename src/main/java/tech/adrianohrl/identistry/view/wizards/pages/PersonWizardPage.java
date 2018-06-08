/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.wizards.pages;

import java.awt.FlowLayout;
import javax.persistence.EntityManager;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.apache.log4j.Logger;
import se.gustavkarlsson.gwiz.AbstractWizardPage;
import se.gustavkarlsson.gwiz.WizardController;
import se.gustavkarlsson.gwiz.wizards.JFrameWizard;
import tech.adrianohrl.dao.DataSource;
import tech.adrianohrl.identistry.exceptions.iDentistryException;
import tech.adrianohrl.identistry.model.individuals.Assistant;
import tech.adrianohrl.identistry.model.individuals.Loggable;
import tech.adrianohrl.identistry.model.individuals.Patient;
import tech.adrianohrl.identistry.model.individuals.Person;
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
    private final Person person;

    /**
     *
     * @param type
     * @param em
     * @param person
     */
    public PersonWizardPage(NewWizardTypes type, EntityManager em, Person person) {
        this.person = person;
        panel = new PersonPanel(this, em, person);
        logger.debug("Created new " + panel.getClass().getSimpleName() + ".");
        switch (type) {
            case NEW_PATIENT:
                if (!(person instanceof Patient)) {
                    throw new iDentistryException("The given person object must be an instance of the Patient class in order to build a Patient object.");
                }
                nextPage = new PatientWizardPage(type, em, (Patient) person);
                break;
            case NEW_ASSISTANT:
            case NEW_DENTIST:
                if (!(person instanceof Loggable)) {
                    throw new iDentistryException("The given person object must be an instance of the Loggable class in order to build an Assistant or a Dentist object.");
                }
                nextPage = new LoggableWizardPage(type, em, (Loggable) person);
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
    
    public static void main(String[] args) {
        IconFontSwing.register(FontAwesome.getIconFont());     
        JFrameWizard wizard = new JFrameWizard("g-wiz demo");
        AbstractWizardPage page = new PersonWizardPage(NewWizardTypes.NEW_ASSISTANT, DataSource.createEntityManager(), new Assistant());
        WizardController wizardController = new WizardController(wizard);
        wizardController.startWizard(page);
        wizard.setVisible(true);
    }
    
}
