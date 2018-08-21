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
import tech.adrianohrl.identistry.control.dao.individuals.PersonDAO;
import tech.adrianohrl.identistry.exceptions.iDentistryException;
import tech.adrianohrl.identistry.model.individuals.Loggable;
import tech.adrianohrl.identistry.model.individuals.Person;
import tech.adrianohrl.identistry.view.panels.PersonPanel;

/**
 *
 * @author adrianohrl
 */
public class PersonWizardPage extends AbstractWizardPage {
    
    private static final Logger logger = Logger.getLogger(PersonWizardPage.class);
    private final AbstractWizardPage nextPage;
    private final PersonPanel panel;
    private final PersonDAO personDAO;
    private final Person person;

    /**
     *
     * @param em
     * @param person
     * @param personDAO
     */
    public PersonWizardPage(EntityManager em, Person person, PersonDAO personDAO) {
        this.person = person;
        this.personDAO = personDAO;
        panel = new PersonPanel(this, em, person);
        logger.debug("Created new " + panel.getClass().getSimpleName() + ".");
        String type = person.getClass().getSimpleName();
        switch (type) {
            case "Patient":
                nextPage = null; // new PatientWizardPage(em, (Patient) person);
                break;
            case "Assistant":
            case "Dentist":
                nextPage = new LoggableWizardPage(em, (Loggable) person);
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
        return person.getClass().getSimpleName().equals("Patient") && panel.isFilled();
    }
    
    public void register() {
        if (!isFinishAllowed()) {
            logger.warn("Unable to register the given person (" + person + ").");
            return;
        }
        personDAO.create(person);
        logger.info("Registered " + person);
    }
    
}
