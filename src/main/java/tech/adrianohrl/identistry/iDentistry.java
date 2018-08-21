/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.apache.log4j.Logger;
import se.gustavkarlsson.gwiz.WizardController;
import tech.adrianohrl.dao.DataSource;
import tech.adrianohrl.dao.DataSourceException;
import tech.adrianohrl.identistry.control.dao.individuals.AssistantDAO;
import tech.adrianohrl.identistry.control.dao.individuals.DentistDAO;
import tech.adrianohrl.identistry.control.dao.individuals.PatientDAO;
import tech.adrianohrl.identistry.view.dialogs.LoginDialog;
import tech.adrianohrl.identistry.model.individuals.Assistant;
import tech.adrianohrl.identistry.model.individuals.Dentist;
import tech.adrianohrl.identistry.model.individuals.Patient;
import tech.adrianohrl.identistry.view.frames.WizardFrame;
import tech.adrianohrl.identistry.view.wizards.pages.PersonWizardPage;

/**
 *
 * @author adrianohrl
 */
public class iDentistry extends javax.swing.JFrame implements EventListener {
    
    private final static Logger logger = Logger.getLogger(iDentistry.class);
    private final EntityManager em = DataSource.createEntityManager();
    private LoginDialog loginDialog;

    /**
     * Creates new form iDentistry
     */
    public iDentistry() {
        initComponents();
        initMyComponents();
    }
    
    /**
     * Creates new form iDentistry
     */
    private void initMyComponents() {
        try {
            loginDialog = new LoginDialog(this, true);
            loginDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            loginDialog.addUserLoginEventListener(new SessionEventListener() {
                @Override
                public void userLoggedIn(SessionEvent evt) {
                    iDentistry.this.setVisible(true);
                    iDentistry.this.loginDialog.setVisible(false);
                    sessionUserNameLabel.setText(evt.getUserName());
                    sessionCountdownTimerLabel.setText(evt.getRemainingDuration());
                }

                @Override
                public void updateRemainingDuration(SessionEvent evt) {
                    sessionCountdownTimerLabel.setText(evt.getRemainingDuration());
                }

                @Override
                public void userLoggedOut(SessionEvent event) {
                    iDentistry.this.setVisible(false);
                    iDentistry.this.loginDialog.setVisible(true);
                    sessionUserNameLabel.setText("");
                    sessionCountdownTimerLabel.setText("-- : -- : --");
                }
            });
            loginDialog.setVisible(true);
            logger.info("Starting iDentistry ...");
        } catch (DataSourceException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error while initializing iDentistry ...", JOptionPane.ERROR_MESSAGE);
            logger.fatal("The database server is not initialized.");              
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        bottomPanel = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        sessionUserLabel = new javax.swing.JLabel();
        sessionUserNameLabel = new javax.swing.JLabel();
        sessionTimerLabel = new javax.swing.JLabel();
        sessionCountdownTimerLabel = new javax.swing.JLabel();
        sessionLogoutButton = new javax.swing.JButton();
        bottomSeparator = new javax.swing.JSeparator();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenu = new javax.swing.JMenu();
        pacientMenuItem = new javax.swing.JMenuItem();
        assistantMenuItem = new javax.swing.JMenuItem();
        dentistMenuItem = new javax.swing.JMenuItem();
        lastFileSeparator = new javax.swing.JPopupMenu.Separator();
        logoutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iDentistry");

        searchLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        searchLabel.setIcon(IconFontSwing.buildIcon(FontAwesome.SEARCH, 15));
        searchLabel.setLabelFor(searchTextField);
        searchLabel.setMaximumSize(new java.awt.Dimension(20, 14));
        searchLabel.setMinimumSize(new java.awt.Dimension(20, 14));
        searchLabel.setPreferredSize(new java.awt.Dimension(20, 14));

        searchTextField.setBackground(new java.awt.Color(51, 51, 51));
        searchTextField.setText("Search... (Ctrl+F)");
        searchTextField.setToolTipText("");
        searchTextField.setMaximumSize(new java.awt.Dimension(150, 25));
        searchTextField.setMinimumSize(new java.awt.Dimension(150, 25));
        searchTextField.setPreferredSize(new java.awt.Dimension(150, 25));

        sessionUserLabel.setText("User:");

        sessionUserNameLabel.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        sessionUserNameLabel.setText(" ");

        sessionTimerLabel.setText("Timeout:");

        sessionCountdownTimerLabel.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        sessionCountdownTimerLabel.setText("-- : -- : --");

        sessionLogoutButton.setIcon(IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 15));
        sessionLogoutButton.setMaximumSize(new java.awt.Dimension(20, 20));
        sessionLogoutButton.setMinimumSize(new java.awt.Dimension(20, 20));
        sessionLogoutButton.setPreferredSize(new java.awt.Dimension(20, 20));
        sessionLogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessionLogoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(sessionUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sessionUserNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sessionTimerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sessionCountdownTimerLabel)
                .addGap(18, 18, 18)
                .addComponent(sessionLogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(bottomSeparator)
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bottomSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(sessionCountdownTimerLabel)
                    .addComponent(sessionTimerLabel)
                    .addComponent(sessionUserNameLabel)
                    .addComponent(sessionUserLabel)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sessionLogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        fileMenu.setText("File");

        newMenu.setIcon(IconFontSwing.buildIcon(FontAwesome.USER_PLUS, 15));
        newMenu.setText("New ...");

        pacientMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        pacientMenuItem.setText("Patient");
        pacientMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(pacientMenuItem);

        assistantMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        assistantMenuItem.setText("Assistant");
        assistantMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assistantMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(assistantMenuItem);

        dentistMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        dentistMenuItem.setText("Dentist");
        dentistMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dentistMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(dentistMenuItem);

        fileMenu.add(newMenu);
        fileMenu.add(lastFileSeparator);

        logoutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        logoutMenuItem.setIcon(IconFontSwing.buildIcon(FontAwesome.SIGN_OUT, 15));
        logoutMenuItem.setText("Logout");
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(logoutMenuItem);

        exitMenuItem.setIcon(IconFontSwing.buildIcon(FontAwesome.POWER_OFF, 15));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        editMenu.setText("Edit");
        mainMenuBar.add(editMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLayeredPane1)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void patientMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientMenuItemActionPerformed
        WizardFrame wizard = new WizardFrame("New patient...");
        WizardController controller = new WizardController(wizard);
        PersonWizardPage page = new PersonWizardPage(em, new Patient(), new PatientDAO(em));
        wizard.getFinishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.register();
            }
        });
        controller.startWizard(page);
        wizard.setVisible(true);
    }//GEN-LAST:event_patientMenuItemActionPerformed

    private void assistantMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assistantMenuItemActionPerformed
        WizardFrame wizard = new WizardFrame("New assistant...");
        WizardController controller = new WizardController(wizard);
        PersonWizardPage page = new PersonWizardPage(em, new Assistant(), new AssistantDAO(em));
        wizard.getFinishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.register();
            }
        });
        controller.startWizard(page);
        wizard.setVisible(true);
    }//GEN-LAST:event_assistantMenuItemActionPerformed

    private void dentistMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dentistMenuItemActionPerformed
        WizardFrame wizard = new WizardFrame("New dentist...");
        WizardController controller = new WizardController(wizard);
        PersonWizardPage page = new PersonWizardPage(em, new Dentist(), new DentistDAO(em));
        wizard.getFinishButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.register();
            }
        });
        controller.startWizard(page);
        wizard.setVisible(true);
    }//GEN-LAST:event_dentistMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuItemActionPerformed
        loginDialog.logout();
    }//GEN-LAST:event_logoutMenuItemActionPerformed

    private void sessionLogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessionLogoutButtonActionPerformed
        loginDialog.logout();
    }//GEN-LAST:event_sessionLogoutButtonActionPerformed

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new iDentistry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem assistantMenuItem;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JSeparator bottomSeparator;
    private javax.swing.JMenuItem dentistMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPopupMenu.Separator lastFileSeparator;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu newMenu;
    private javax.swing.JMenuItem pacientMenuItem;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel sessionCountdownTimerLabel;
    private javax.swing.JButton sessionLogoutButton;
    private javax.swing.JLabel sessionTimerLabel;
    private javax.swing.JLabel sessionUserLabel;
    private javax.swing.JLabel sessionUserNameLabel;
    // End of variables declaration//GEN-END:variables
}
