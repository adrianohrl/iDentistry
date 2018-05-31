/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.frames;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.apache.log4j.Logger;
import se.gustavkarlsson.gwiz.Wizard;
import se.gustavkarlsson.gwiz.WizardController;
import tech.adrianohrl.identistry.view.wizards.pages.PersonWizardPage;

/**
 *
 * @author adrianohrl
 */
public class WizardFrame extends javax.swing.JFrame implements Wizard {
    
    private static final Dimension defaultminimumSize = new Dimension(450, 450);
    private final static Logger logger = Logger.getLogger(WizardFrame.class);

    /**
     * Creates new form WizardFrame
     * @param title
     */
    public WizardFrame(String title) {
        super(title);
        initComponents();
        setMinimumSize(defaultminimumSize);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPosition = (screenSize.width / 2) - (defaultminimumSize.width / 2);
        int yPosition = (screenSize.height / 2) - (defaultminimumSize.height / 2);
        setLocation(xPosition, yPosition);
        wizardPageContainer.addContainerListener(new WizardFrame.MinimumSizeAdjuster());
        
        GridBagConstraints wizardPageContainerConstraint = new GridBagConstraints();
		wizardPageContainerConstraint.gridwidth = 5;
		wizardPageContainerConstraint.fill = GridBagConstraints.BOTH;
		wizardPageContainerConstraint.gridx = 0;
		wizardPageContainerConstraint.gridy = 0;
		wizardPageContainerConstraint.insets = new Insets(5, 5, 5, 5);
		getContentPane().add(wizardPageContainer, wizardPageContainerConstraint);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        wizardPageContainer = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        getRootPane().registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        finishButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        buttonsSeparator = new javax.swing.JSeparator();
        nextButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[]{0};
        layout.rowHeights = new int[]{0, 0};
        layout.columnWeights = new double[]{1.0};
        layout.rowWeights = new double[]{1.0, 0.0};
        getContentPane().setLayout(layout);

        wizardPageContainer.setPreferredSize(new java.awt.Dimension(1, 1));
        wizardPageContainer.setLayout(new java.awt.GridLayout(1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 12);
        getContentPane().add(wizardPageContainer, gridBagConstraints);

        finishButton.setIcon(IconFontSwing.buildIcon(FontAwesome.CHECK, 16));
        finishButton.setText("Finish");
        finishButton.setMaximumSize(new java.awt.Dimension(100, 30));
        finishButton.setMinimumSize(new java.awt.Dimension(100, 30));
        finishButton.setPreferredSize(new java.awt.Dimension(100, 30));
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        cancelButton.setIcon(IconFontSwing.buildIcon(FontAwesome.TIMES, 16));
        cancelButton.setText("Cancel");
        cancelButton.setMaximumSize(new java.awt.Dimension(100, 30));
        cancelButton.setMinimumSize(new java.awt.Dimension(100, 30));
        cancelButton.setPreferredSize(new java.awt.Dimension(100, 30));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        buttonsSeparator.setName(""); // NOI18N

        nextButton.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_RIGHT, 16));
        nextButton.setText("Next");
        nextButton.setMaximumSize(new java.awt.Dimension(100, 30));
        nextButton.setMinimumSize(new java.awt.Dimension(100, 30));
        nextButton.setPreferredSize(new java.awt.Dimension(100, 30));

        previousButton.setIcon(IconFontSwing.buildIcon(FontAwesome.ARROW_LEFT, 16));
        previousButton.setText("Back");
        previousButton.setMaximumSize(new java.awt.Dimension(100, 30));
        previousButton.setMinimumSize(new java.awt.Dimension(100, 30));
        previousButton.setPreferredSize(new java.awt.Dimension(100, 30));

        helpButton.setIcon(IconFontSwing.buildIcon(FontAwesome.QUESTION_CIRCLE, 16));
        helpButton.setText("Help");
        helpButton.setEnabled(false);
        helpButton.setMaximumSize(new java.awt.Dimension(100, 30));
        helpButton.setMinimumSize(new java.awt.Dimension(100, 30));
        helpButton.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsSeparator)
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                        .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buttonsSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 225;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 12);
        getContentPane().add(buttonsPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        dispose();
    }//GEN-LAST:event_finishButtonActionPerformed

    @Override
    public Container getWizardPageContainer() {
        return wizardPageContainer;
    }

    @Override
    public AbstractButton getCancelButton() {
        return cancelButton;
    }

    @Override
    public AbstractButton getPreviousButton() {
        return previousButton;
    }

    @Override
    public JButton getNextButton() {
        return nextButton;
    }

    @Override
    public JButton getFinishButton() {
        return finishButton;
    }

    @Override
    public void setDefautButton(JButton button) {
        getRootPane().setDefaultButton(button);
    }
    
    private class MinimumSizeAdjuster implements ContainerListener {

        @Override
        public void componentAdded(ContainerEvent e) {
                Dimension currentSize = getSize();
                Dimension preferredSize = getPreferredSize();
                Dimension newSize = new Dimension(currentSize);
                newSize.width = Math.max(currentSize.width, preferredSize.width);
                newSize.height = Math.max(currentSize.height, preferredSize.height);
                setMinimumSize(newSize);
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
        }

	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JSeparator buttonsSeparator;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton finishButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JPanel wizardPageContainer;
    // End of variables declaration//GEN-END:variables

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        IconFontSwing.register(FontAwesome.getIconFont());
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.error(ex);
        }
        WizardFrame wizard = new WizardFrame("New Person...");
        WizardController controller = new WizardController(wizard);
        wizard.setVisible(true);
        PersonWizardPage page = new PersonWizardPage();
        controller.startWizard(page);
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WizardFrame wizard = new WizardFrame();
                WizardController controller = new WizardController(wizard);
                wizard.setVisible(true);
                PersonWizardPage page = new PersonWizardPage();
                controller.startWizard(page);
            }
        });*/
    }
    
}
