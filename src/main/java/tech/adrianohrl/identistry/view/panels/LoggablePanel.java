/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.panels;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.Icon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.Configuration;
import me.gosimple.nbvcxz.resources.ConfigurationBuilder;
import me.gosimple.nbvcxz.resources.Dictionary;
import me.gosimple.nbvcxz.resources.DictionaryBuilder;
import me.gosimple.nbvcxz.scoring.Result;
import org.apache.log4j.Logger;
import se.gustavkarlsson.gwiz.AbstractWizardPage;
import tech.adrianohrl.identistry.control.dao.individuals.LoggableDAO;
import tech.adrianohrl.util.PropertyUtil;

/**
 *
 * @author adrianohrl
 */
public class LoggablePanel extends AbstractWizardPagePanel {

    private static final Logger logger = Logger.getLogger(LoggablePanel.class);
    private final LoggableDAO dao;
    private final Nbvcxz nbvcxz = new Nbvcxz();
    private final double LOW_ENTROPY = PropertyUtil.getLowPasswordEntropyThreshold();
    private final double HIGH_ENTROPY = PropertyUtil.getHighPasswordEntropyThreshold();
    private final double strengthFactor;

    /**
     * Creates new form LoggablePanel
     *
     * @param parent
     * @param em
     */
    public LoggablePanel(AbstractWizardPage parent, EntityManager em) {
        super(parent, em);
        this.dao = new LoggableDAO(em);
        initComponents();
        setMandatoryFieldsListeners();
        configureNbvcxz();
        strengthFactor = 100 / 3 / (HIGH_ENTROPY - LOW_ENTROPY);
    }

    @Override
    public boolean isFilled() {
        return isValidUsername() && isValidPassword();
    }

    @Override
    public Component getFirstFocusableComponent() {
        return usernameTextField;
    }

    @Override
    public void setLastFocusableComponent(Component component) {
        confirmPasswordField.setNextFocusableComponent(component);
    }

    private boolean isValidUsername() {
        String username = usernameTextField.getText();
        return !username.isEmpty() && !dao.isRegistered(username);
    }

    private boolean isValidPassword() {
        char[] password = passwordField.getPassword();
        char[] confirmPassword = confirmPasswordField.getPassword();
        if (password.length == 0 || password.length != confirmPassword.length) {
            return false;
        }
        for (int i = 0; i < passwordField.getPassword().length; i++) {
            if (password[i] != confirmPassword[i]) {
                return false;
            }
        }
        return true;
    }

    private void validateUsername() {
        Icon icon = isValidUsername() 
                ? IconFontSwing.buildIcon(FontAwesome.CHECK, 16, Color.GREEN) 
                : IconFontSwing.buildIcon(FontAwesome.TIMES, 16, Color.RED);
        usernameCheckLabel.setIcon(icon);
    }

    private void calculatePasswordStrength() {
        String password = new String(passwordField.getPassword());
        Result result = nbvcxz.estimate(password);
        Double strength = strengthFactor * result.getEntropy();
        strengthProgressBar.setValue(strength.intValue());  
        confirmPasswordField.setEnabled(result.isMinimumEntropyMet());        
    }
    
    private void configureNbvcxz() {
        List<Dictionary> dictionaryList = ConfigurationBuilder.getDefaultDictionaries();
        if (!usernameTextField.getText().isEmpty()) {
            dictionaryList.add(new DictionaryBuilder()
                    .setDictionaryName("exclude")
                    .setExclusion(true)
                    .addWord(usernameTextField.getText(), 0)
                    .createDictionary());
        }
        Configuration configuration = new ConfigurationBuilder()
                .setLocale(PropertyUtil.getDefaultLocale())
                .setMinimumEntropy(LOW_ENTROPY)
                .setDictionaries(dictionaryList)
                .createConfiguration();
        nbvcxz.setConfiguration(configuration);
    }

    private void validatePasswords() {
        Icon icon = isValidPassword() 
                ? IconFontSwing.buildIcon(FontAwesome.CHECK, 16, Color.GREEN) 
                : IconFontSwing.buildIcon(FontAwesome.TIMES, 16, Color.RED);
        confirmPasswordCheckLabel.setIcon(icon);
    }

    @Override
    protected void setMandatoryFieldsListeners() {
        listener.assignToListenerList(usernameTextField);
        usernameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateUsername();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateUsername();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateUsername();
            }
        });
        listener.assignToListenerList(passwordField);
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculatePasswordStrength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculatePasswordStrength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculatePasswordStrength();
            }
        });
        listener.assignToListenerList(confirmPasswordField);
        confirmPasswordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validatePasswords();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validatePasswords();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validatePasswords();
            }
        });
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

        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        usernameCheckLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        confirmPasswordLabel = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        confirmPasswordCheckLabel = new javax.swing.JLabel();
        strengthLabel = new javax.swing.JLabel();
        strengthProgressBar = new javax.swing.JProgressBar();

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {100, 150, 20};
        layout.rowHeights = new int[] {25, 25, 25, 25};
        setLayout(layout);

        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usernameLabel.setText("Username *:");
        usernameLabel.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(usernameLabel, gridBagConstraints);

        usernameTextField.setToolTipText("Enter the username for login.");
        usernameTextField.setMaximumSize(new java.awt.Dimension(150, 25));
        usernameTextField.setMinimumSize(new java.awt.Dimension(150, 25));
        usernameTextField.setNextFocusableComponent(passwordField);
        usernameTextField.setPreferredSize(new java.awt.Dimension(150, 25));
        usernameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(usernameTextField, gridBagConstraints);

        usernameCheckLabel.setIcon(IconFontSwing.buildIcon(FontAwesome.TIMES, 16, Color.RED));
        usernameCheckLabel.setMaximumSize(new java.awt.Dimension(20, 20));
        usernameCheckLabel.setMinimumSize(new java.awt.Dimension(20, 20));
        usernameCheckLabel.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(usernameCheckLabel, gridBagConstraints);

        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        passwordLabel.setText("Password *:");
        passwordLabel.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(passwordLabel, gridBagConstraints);

        passwordField.setToolTipText("Enter the desired password for login.");
        passwordField.setMaximumSize(new java.awt.Dimension(150, 25));
        passwordField.setMinimumSize(new java.awt.Dimension(150, 25));
        passwordField.setNextFocusableComponent(confirmPasswordField);
        passwordField.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(passwordField, gridBagConstraints);

        confirmPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        confirmPasswordLabel.setText("Confirm password *:");
        confirmPasswordLabel.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(confirmPasswordLabel, gridBagConstraints);

        confirmPasswordField.setToolTipText("Re-enter the desired password for confirmation.");
        confirmPasswordField.setEnabled(false);
        confirmPasswordField.setMaximumSize(new java.awt.Dimension(150, 25));
        confirmPasswordField.setMinimumSize(new java.awt.Dimension(150, 25));
        confirmPasswordField.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(confirmPasswordField, gridBagConstraints);

        confirmPasswordCheckLabel.setIcon(IconFontSwing.buildIcon(FontAwesome.TIMES, 16, Color.RED));
        confirmPasswordCheckLabel.setMaximumSize(new java.awt.Dimension(20, 20));
        confirmPasswordCheckLabel.setMinimumSize(new java.awt.Dimension(20, 20));
        confirmPasswordCheckLabel.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(confirmPasswordCheckLabel, gridBagConstraints);

        strengthLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        strengthLabel.setText("Strength:");
        strengthLabel.setFocusable(false);
        strengthLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(strengthLabel, gridBagConstraints);

        strengthProgressBar.setForeground(new java.awt.Color(0, 0, 255));
        strengthProgressBar.setToolTipText("The password strength.");
        strengthProgressBar.setValue(10);
        strengthProgressBar.setFocusable(false);
        strengthProgressBar.setMaximumSize(new java.awt.Dimension(150, 14));
        strengthProgressBar.setMinimumSize(new java.awt.Dimension(150, 14));
        strengthProgressBar.setPreferredSize(new java.awt.Dimension(150, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(strengthProgressBar, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextFieldFocusLost
        configureNbvcxz();
    }//GEN-LAST:event_usernameTextFieldFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel confirmPasswordCheckLabel;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel strengthLabel;
    private javax.swing.JProgressBar strengthProgressBar;
    private javax.swing.JLabel usernameCheckLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
