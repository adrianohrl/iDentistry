/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.wizards.pages;

import java.awt.FlowLayout;
import se.gustavkarlsson.gwiz.AbstractWizardPage;
import tech.adrianohrl.identistry.view.panels.PersonPanel;

/**
 *
 * @author adrianohrl
 */
public class PersonWizardPage extends AbstractWizardPage {
    
    private final AbstractWizardPage nextPage = null;
    private final PersonPanel panel = new PersonPanel();

    public PersonWizardPage() {
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
        return true;
    }
    
}
