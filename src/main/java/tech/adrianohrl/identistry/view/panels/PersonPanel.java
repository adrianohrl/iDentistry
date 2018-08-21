/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.view.panels;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.apache.log4j.Logger;
import se.gustavkarlsson.gwiz.AbstractWizardPage;
import tech.adrianohrl.identistry.control.dao.individuals.PersonDAO;
import tech.adrianohrl.identistry.model.individuals.Address;
import tech.adrianohrl.util.AddressUtil;
import tech.adrianohrl.identistry.model.individuals.Genders;
import tech.adrianohrl.identistry.model.individuals.Person;
import tech.adrianohrl.identistry.view.components.ImageUtil;
import tech.adrianohrl.util.CalendarFormat;

/**
 *
 * @author adrianohrl
 */
public class PersonPanel extends AbstractWizardPagePanel {
    
    private static final Logger logger = Logger.getLogger(PersonPanel.class);    
    private final PersonDAO dao;
    private final Person person;

    /**
     * Creates new form NewPersonPanel
     * @param parent
     * @param em
     * @param person
     */
    public PersonPanel(AbstractWizardPage parent, EntityManager em, Person person) {
        super(parent, em);
        this.dao = new PersonDAO(em);
        this.person = person;
        initComponents();
        load();
        setListeners();
    }
    
    /*public String getPersonName() {
        return nameTextField.getText();
    }
    
    public String getPersonProfilePicture() {
        ImageIcon icon = (ImageIcon) pictureLabel.getIcon();
        return icon != null ? icon.getDescription() : "";
    }
    
    public Genders getPersonGender() {
        ButtonModel radioButton = genderButtonGroup.getSelection();
        return radioButton != null ? Genders.getInstance(radioButton.getActionCommand()) : null;
    }
    
    public Calendar getPersonDayOfBirth() {
        if (dobFormattedTextField.getText().isEmpty()) {
            return null;
        }
        Calendar dob = Calendar.getInstance();
        dob.setTime((Date) dobFormattedTextField.getValue());
        return dob;
    }
    
    public String getPersonPhone() {
        return (String) phoneFormattedTextField.getValue();
    }
    
    public boolean getPersonPhoneWhatsApp() {
        return whatsappCheckBox.isSelected();
    }
    
    public String getPersonCPF() {
        return (String) cpfFormattedTextField.getValue();
    }
    
    public String getPersonRG() {
        return rgTextField.getText();
    }
    
    public String getPersonAddressStreet() {
        return streetTextField.getText();
    }
    
    public int getPersonAddressNumber() {
        Long number = (Long) numberFormattedTextField.getValue();
        return number != null ? number.intValue() : 0;
    }
    
    public String getPersonAddressObservation() {
        return obsTextField.getText();
    }
    
    public String getPersonAddressArea() {
        return areaTextField.getText();
    }
    
    public String getPersonAddressZIP() {
        String zip = (String) zipFormattedTextField.getValue();
        return zip != null ? zip : "";
    }
    
    public String getPersonAddressCity() {
        String city = (String) cityComboBox.getSelectedItem();
        return city != null ? city : "";
    }
    
    public String getPersonAddressState() {
        String state = (String) stateComboBox.getSelectedItem();
        return state != null ? state : "";
    }
    
    public String getPersonAddressCountry() {
        return "Brasil";
    }
    
    public Address getPersonAddress() {
        String street = getPersonAddressStreet();
        int number = getPersonAddressNumber();
        String obs = getPersonAddressObservation();
        String area = getPersonAddressArea();
        String zip = getPersonAddressZIP();
        String city = getPersonAddressCity();
        String state = getPersonAddressState();
        String country = getPersonAddressCountry();
        return new Address(street, number, area, obs, zip, city, state, country);
    }
    
    public String getPersonEmail() {
        return emailTextField.getText();
    }
    
    public String getPersonFacebook() {
        return facebookLabel.getText() + facebookTextField.getText();
    }
    
    public String getPersonInstagram() {
        return instagramLabel.getText() + instagramTextField.getText();
    }
    
    public String getPersonOccupation() {
        return occupationTextField.getText();
    }
    
    private boolean isAddressFilled() {
        Address address = getPersonAddress();
        return !address.getStreet().isEmpty()
            && address.getNumber() > 0
            && !address.getArea().isEmpty()
            && !address.getCity().isEmpty()
            && !address.getUf().isEmpty()
            && !address.getCountry().isEmpty();
    }
            
    @Override
    public boolean isFilled() {
        return !getPersonName().isEmpty() 
            && getPersonGender() != null
            && getPersonDayOfBirth() != null
            && !getPersonPhone().isEmpty()
            && !getPersonRG().isEmpty()
            && isAddressFilled();
            //&& !getPersonProfilePicture().isEmpty();
    }*/
            
    @Override
    public boolean isFilled() {
        Address address = person.getAddress();
        return person != null 
            && person.getName() != null && !person.getName().isEmpty() 
            && person.getGender() != null
            && person.getDob() != null
            && person.getPhone() != null && !person.getPhone().isEmpty()
            && person.getRg() != null && !person.getRg().isEmpty()
            && person.getCpf() != null && !person.getCpf().isEmpty()
            && address != null
            && address.getStreet() != null && !address.getStreet().isEmpty()
            && address.getNumber() > 0
            && address.getArea() != null && !address.getArea().isEmpty()
            && address.getCity() != null && !address.getCity().isEmpty()
            && address.getUf() != null && !address.getUf().isEmpty();
            //&& !getPersonProfilePicture().isEmpty();
    }

    @Override
    public Component getFirstFocusableComponent() {
        return nameTextField;
    }

    @Override
    public void setLastFocusableComponent(Component component) {
        pictureLabel.setNextFocusableComponent(component);
    }
    
    private ComboBoxModel getStates() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("");
        for (String state : AddressUtil.getStates()) {
            model.addElement(state);
        }
        model.setSelectedItem("");
        return model;
    }
    
    private ComboBoxModel getCities(String state) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("");
        for (String city : AddressUtil.getStateCities(state)) {
            model.addElement(city);
        }
        model.setSelectedItem("");
        return model;
    }
    
    private void editProfilePicture() {
        ImageIcon icon = ImageUtil.chooseImageIcon(this);
        pictureRemoveButton.setEnabled(true);
        if (icon == null) {
            pictureRemoveButton.setEnabled(false);
            icon = ImageUtil.getDefaultProfilePicture();
        }
        pictureLabel.setIcon(icon);
        parent.updateWizardButtons();
    }
    
    private void removeProfilePicture() {
        pictureLabel.setIcon(ImageUtil.getDefaultProfilePicture());
        parent.updateWizardButtons();
    }

    private void setListeners() {
        nameTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.setName(str);
                logger.trace("Changed person's " + "name: " + person.getName());
                System.out.println("Changed person's " + "name: " + person.getName());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        pictureLabel.addPropertyChangeListener("icon", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ImageIcon icon = (ImageIcon) evt.getNewValue();
                person.setPicture(icon != null ? icon.getDescription() : "");
                logger.trace("Changed person's " + "picture: " + person.getPicture());
                System.out.println("Changed person's " + "picture: " + person.getPicture());
            }
        });
        genderFemaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JRadioButton radioButton = (JRadioButton) evt.getSource();
                person.setGender(radioButton.isSelected() ? Genders.FEMALE : Genders.MALE);
                logger.trace("Changed person's " + "gender: " + person.getGender());
                System.out.println("Changed person's " + "gender: " + person.getGender());
            }
        });
        genderMaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JRadioButton radioButton = (JRadioButton) evt.getSource();
                person.setGender(radioButton.isSelected() ? Genders.MALE : Genders.FEMALE);
                logger.trace("Changed person's " + "gender: " + person.getGender());
                System.out.println("Changed person's " + "gender: " + person.getGender());
            }
        });
        dobFormattedTextField.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime((Date) evt.getNewValue());
                person.setDob(calendar);
                logger.trace("Changed person's " + "day-of-birth: " + CalendarFormat.formatDate(person.getDob()));
                System.out.println("Changed person's " + "day-of-birth: " + CalendarFormat.formatDate(person.getDob()));
            }
        });
        phoneFormattedTextField.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                person.setPhone((String) evt.getNewValue());
                logger.trace("Changed person's " + "phone: " + person.getPhone());
                System.out.println("Changed person's " + "phone: " + person.getPhone());
            }
        });
        whatsappCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JCheckBox checkBox = (JCheckBox) evt.getSource();
                person.setWhatsapp(checkBox.isSelected());
                logger.trace("Changed person's " + "whatsapp: " + (person.isWhatsapp() ? "YES" : "NO"));
                System.out.println("Changed person's " + "whatsapp: " + (person.isWhatsapp() ? "YES" : "NO"));
            }
        });
        cpfFormattedTextField.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                person.setCpf((String) evt.getNewValue());
                logger.trace("Changed person's " + "cpf: " + person.getCpf());
                System.out.println("Changed person's " + "cpf: " + person.getCpf());
            }
        });
        rgTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.setRg(str);
                logger.trace("Changed person's " + "rg: " + person.getRg());
                System.out.println("Changed person's " + "rg: " + person.getRg());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        occupationTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.setOccupation(str);
                logger.trace("Changed person's " + "occupation: " + person.getOccupation());
                System.out.println("Changed person's " + "occupation: " + person.getOccupation());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        streetTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.getAddress().setStreet(str);
                logger.trace("Changed person's address " + "street: " + person.getAddress().getStreet());
                System.out.println("Changed person's address " + "street: " + person.getAddress().getStreet());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        numberFormattedTextField.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Long number = (Long) evt.getNewValue();
                person.getAddress().setNumber(number != null ? number.intValue() : 0);
                logger.trace("Changed person's address " + "number: " + person.getAddress().getNumber());
                System.out.println("Changed person's address " + "number: " + person.getAddress().getNumber());
            }
        });
        obsTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.getAddress().setObs(str);
                logger.trace("Changed person's address " + "obs: " + person.getAddress().getObs());
                System.out.println("Changed person's address " + "obs: " + person.getAddress().getObs());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        areaTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.getAddress().setArea(str);
                logger.trace("Changed person's address " + "area: " + person.getAddress().getArea());
                System.out.println("Changed person's address " + "area: " + person.getAddress().getArea());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        zipFormattedTextField.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                person.getAddress().setZip((String) evt.getNewValue());
                logger.trace("Changed person's address " + "zip: " + person.getAddress().getZip());
                System.out.println("Changed person's address " + "zip: " + person.getAddress().getZip());
            }
        });
        stateComboBox.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.getAddress().setUf(str);
                logger.trace("Changed person's address " + "state: " + person.getAddress().getUf());
                System.out.println("Changed person's address " + "state: " + person.getAddress().getUf());
            }

            @Override
            public void focusGained(FocusEvent evt) {
            }

            @Override
            public void focusLost(FocusEvent evt) {
                JComboBox comboBox = (JComboBox) evt.getSource();
                update((String) comboBox.getSelectedItem());
            }
        });
        cityComboBox.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.getAddress().setCity(str);
                logger.trace("Changed person's address " + "city: " + person.getAddress().getCity());
                System.out.println("Changed person's address " + "city: " + person.getAddress().getCity());
            }

            @Override
            public void focusGained(FocusEvent evt) {
            }

            @Override
            public void focusLost(FocusEvent evt) {
                JComboBox comboBox = (JComboBox) evt.getSource();
                update((String) comboBox.getSelectedItem());
            }
        });
        emailTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.setEmail(str);
                logger.trace("Changed person's " + "email: " + person.getEmail());
                System.out.println("Changed person's " + "email: " + person.getEmail());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        facebookTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.setFacebook(str);
                logger.trace("Changed person's " + "facebook: " + person.getFacebook());
                System.out.println("Changed person's " + "facebook: " + person.getFacebook());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        instagramTextField.addFocusListener(new FocusListener() {
            private void update(String str) {
                person.setInstagram(str);
                logger.trace("Changed person's " + "instagram: " + person.getInstagram());
                System.out.println("Changed person's " + "instagram: " + person.getInstagram());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                update(textField.getText());
            }
        });
        listener.assignToListenerList(nameTextField);
        listener.assignToListenerList(dobFormattedTextField);
        listener.assignToListenerList(phoneFormattedTextField);
        listener.assignToListenerList(rgTextField);
        listener.assignToListenerList(streetTextField);
        listener.assignToListenerList(numberFormattedTextField);
        listener.assignToListenerList(areaTextField);
    }
    
    private void load() {
        nameTextField.setText(person.getName());
        pictureLabel.setIcon(ImageUtil.getProfilePicture(person.getPicture()));
        Genders gender = person.getGender();
        genderFemaleRadioButton.setSelected(gender != null && gender.isFemale());
        genderMaleRadioButton.setSelected(gender != null && gender.isMale());
        dobFormattedTextField.setText(CalendarFormat.formatDate(person.getDob()));
        phoneFormattedTextField.setText(person.getPhone());
        whatsappCheckBox.setSelected(person.isWhatsapp());
        cpfFormattedTextField.setText(person.getCpf());
        rgTextField.setText(person.getRg());
        occupationTextField.setText(person.getOccupation());
        Address address = person.getAddress();
        if (address == null) {
            address = new Address();
            person.setAddress(address);
        }
        streetTextField.setText(address.getStreet());
        numberFormattedTextField.setText(address.getNumber() + "");
        obsTextField.setText(address.getObs());
        areaTextField.setText(address.getArea());
        zipFormattedTextField.setText(address.getZip());
        stateComboBox.setSelectedItem(address.getUf());
        if (address.getUf() != null && !address.getUf().isEmpty()) {
            cityComboBox.setSelectedItem(address.getCity());
            cityComboBox.setEnabled(true);
        }
        emailTextField.setText(person.getEmail());
        facebookTextField.setText(person.getFacebook());
        instagramTextField.setText(person.getInstagram());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderButtonGroup = new javax.swing.ButtonGroup();
        personPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        genderLabel = new javax.swing.JLabel();
        genderPanel = new javax.swing.JPanel();
        genderFemaleRadioButton = new javax.swing.JRadioButton();
        genderMaleRadioButton = new javax.swing.JRadioButton();
        dobLabel = new javax.swing.JLabel();
        dobFormattedTextField = new javax.swing.JFormattedTextField();
        phoneLabel = new javax.swing.JLabel();
        phoneFormattedTextField = new javax.swing.JFormattedTextField();
        whatsappLabelIcon = new javax.swing.JLabel();
        whatsappLabel = new javax.swing.JLabel();
        whatsappCheckBox = new javax.swing.JCheckBox();
        cpfLabel = new javax.swing.JLabel();
        cpfFormattedTextField = new javax.swing.JFormattedTextField();
        rgLabel = new javax.swing.JLabel();
        rgTextField = new javax.swing.JTextField();
        occupationLabel = new javax.swing.JLabel();
        occupationTextField = new javax.swing.JTextField();
        addressPanel = new javax.swing.JPanel();
        streetLabel = new javax.swing.JLabel();
        streetTextField = new javax.swing.JTextField();
        numberLabel = new javax.swing.JLabel();
        numberFormattedTextField = new javax.swing.JFormattedTextField();
        areaLabel = new javax.swing.JLabel();
        areaTextField = new javax.swing.JTextField();
        obsLabel = new javax.swing.JLabel();
        obsTextField = new javax.swing.JTextField();
        zipLabel = new javax.swing.JLabel();
        zipFormattedTextField = new javax.swing.JFormattedTextField();
        stateLabel = new javax.swing.JLabel();
        stateComboBox = new javax.swing.JComboBox();
        cityLabel = new javax.swing.JLabel();
        cityComboBox = new javax.swing.JComboBox();
        picturePanel = new javax.swing.JPanel();
        pictureLabel = new javax.swing.JLabel();
        pictureRemoveButton = new javax.swing.JButton();
        pictureEditButton = new javax.swing.JButton();
        socialNetworksPanel = new javax.swing.JPanel();
        facebookLabelIcon = new javax.swing.JLabel();
        facebookLabel1 = new javax.swing.JLabel();
        facebookLabel = new javax.swing.JLabel();
        facebookTextField = new javax.swing.JTextField();
        intagramLabelIcon = new javax.swing.JLabel();
        instagramLabel1 = new javax.swing.JLabel();
        instagramLabel = new javax.swing.JLabel();
        instagramTextField = new javax.swing.JTextField();
        emailLabelIcon = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();

        genderButtonGroup.add(genderFemaleRadioButton);
        genderButtonGroup.add(genderMaleRadioButton);

        setMinimumSize(new java.awt.Dimension(900, 500));
        setNextFocusableComponent(nameTextField);

        personPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal Data"));

        nameLabel.setLabelFor(nameTextField);
        nameLabel.setText("Name *:");
        nameLabel.setFocusable(false);

        nameTextField.setToolTipText("Enter the name.");
        nameTextField.setNextFocusableComponent(genderFemaleRadioButton);

        genderLabel.setLabelFor(genderPanel);
        genderLabel.setText("Gender *:");
        genderLabel.setFocusable(false);

        genderPanel.setToolTipText("Select the gender.");
        genderPanel.setMaximumSize(new java.awt.Dimension(20, 100));
        genderPanel.setMinimumSize(new java.awt.Dimension(20, 22));

        genderFemaleRadioButton.setText("Female");
        genderFemaleRadioButton.setActionCommand("Female");
        genderFemaleRadioButton.setNextFocusableComponent(genderMaleRadioButton);
        genderFemaleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderFemaleRadioButtonActionPerformed(evt);
            }
        });

        genderMaleRadioButton.setText("Male");
        genderMaleRadioButton.setActionCommand("Male");
        genderMaleRadioButton.setNextFocusableComponent(dobFormattedTextField);
        genderMaleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderMaleRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout genderPanelLayout = new javax.swing.GroupLayout(genderPanel);
        genderPanel.setLayout(genderPanelLayout);
        genderPanelLayout.setHorizontalGroup(
            genderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genderPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(genderFemaleRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(genderMaleRadioButton)
                .addContainerGap())
        );
        genderPanelLayout.setVerticalGroup(
            genderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(genderMaleRadioButton)
                .addComponent(genderFemaleRadioButton))
        );

        dobLabel.setLabelFor(dobFormattedTextField);
        dobLabel.setText("Day of Birth *:");
        dobLabel.setFocusable(false);

        dobFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        dobFormattedTextField.setToolTipText("Enter the day-of-birth.");
        dobFormattedTextField.setNextFocusableComponent(phoneFormattedTextField);
        dobFormattedTextField.setValue(new Date());

        phoneLabel.setText("Phone *:");
        phoneLabel.setFocusable(false);

        try {
            phoneFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        phoneFormattedTextField.setToolTipText("Enter the phone number.");
        phoneFormattedTextField.setNextFocusableComponent(whatsappCheckBox);
        phoneFormattedTextField.setValue("");

        whatsappLabelIcon.setIcon(IconFontSwing.buildIcon(FontAwesome.WHATSAPP, 20));
        whatsappLabelIcon.setLabelFor(whatsappCheckBox);
        whatsappLabelIcon.setFocusable(false);
        whatsappLabelIcon.setMaximumSize(new java.awt.Dimension(21, 21));
        whatsappLabelIcon.setMinimumSize(new java.awt.Dimension(21, 21));
        whatsappLabelIcon.setPreferredSize(new java.awt.Dimension(21, 21));

        whatsappLabel.setText(":");
        whatsappLabel.setFocusable(false);

        whatsappCheckBox.setToolTipText("Select if the associated phone number has WhatsApp.");
        whatsappCheckBox.setNextFocusableComponent(cpfFormattedTextField);

        cpfLabel.setLabelFor(cpfFormattedTextField);
        cpfLabel.setText("CPF *:");
        cpfLabel.setFocusable(false);

        try {
            cpfFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cpfFormattedTextField.setToolTipText("Enter the CPF number.");
        cpfFormattedTextField.setNextFocusableComponent(rgTextField);
        cpfFormattedTextField.setValue("");

        rgLabel.setLabelFor(rgTextField);
        rgLabel.setText("RG *:");
        rgLabel.setFocusable(false);

        rgTextField.setToolTipText("Enter the RG number.");
        rgTextField.setNextFocusableComponent(occupationTextField);

        occupationLabel.setText("Occupation:");
        occupationLabel.setFocusable(false);

        occupationTextField.setNextFocusableComponent(streetTextField);

        javax.swing.GroupLayout personPanelLayout = new javax.swing.GroupLayout(personPanel);
        personPanel.setLayout(personPanelLayout);
        personPanelLayout.setHorizontalGroup(
            personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cpfLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(occupationLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dobLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(genderLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personPanelLayout.createSequentialGroup()
                        .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dobFormattedTextField)
                            .addComponent(cpfFormattedTextField)
                            .addComponent(occupationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rgLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phoneFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(rgTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(whatsappLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(whatsappLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(whatsappCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(nameTextField))
                .addContainerGap())
        );

        personPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cpfFormattedTextField, dobFormattedTextField, genderPanel, occupationTextField, phoneFormattedTextField, rgTextField});

        personPanelLayout.setVerticalGroup(
            personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(genderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(whatsappLabel)
                    .addComponent(whatsappCheckBox)
                    .addComponent(whatsappLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneLabel)
                    .addComponent(dobFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dobLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cpfLabel)
                    .addComponent(cpfFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rgLabel)
                    .addComponent(rgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(personPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(occupationLabel)
                    .addComponent(occupationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        personPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cpfFormattedTextField, cpfLabel, dobFormattedTextField, dobLabel, genderLabel, genderPanel, nameLabel, nameTextField, occupationLabel, occupationTextField, phoneFormattedTextField, phoneLabel, rgLabel, rgTextField, whatsappCheckBox, whatsappLabel, whatsappLabelIcon});

        addressPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Address"));

        streetLabel.setLabelFor(streetTextField);
        streetLabel.setText("Street *:");
        streetLabel.setFocusable(false);

        streetTextField.setToolTipText("Enter the street name.");
        streetTextField.setMinimumSize(new java.awt.Dimension(400, 18));
        streetTextField.setNextFocusableComponent(numberFormattedTextField);
        streetTextField.setPreferredSize(new java.awt.Dimension(400, 18));

        numberLabel.setLabelFor(numberFormattedTextField);
        numberLabel.setText("Number *:");
        numberLabel.setFocusable(false);

        numberFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        numberFormattedTextField.setToolTipText("Enter the house number.");
        numberFormattedTextField.setNextFocusableComponent(obsTextField);

        areaLabel.setLabelFor(areaTextField);
        areaLabel.setText("Area *:");
        areaLabel.setFocusable(false);

        areaTextField.setToolTipText("Enter the city area name.");
        areaTextField.setNextFocusableComponent(zipFormattedTextField);

        obsLabel.setLabelFor(obsTextField);
        obsLabel.setText("Observation:");
        obsLabel.setFocusable(false);

        obsTextField.setToolTipText("Enter additional informations about the address.");
        obsTextField.setMinimumSize(new java.awt.Dimension(400, 18));
        obsTextField.setNextFocusableComponent(areaTextField);
        obsTextField.setPreferredSize(new java.awt.Dimension(400, 18));

        zipLabel.setLabelFor(zipFormattedTextField);
        zipLabel.setText("ZIP Code:");
        zipLabel.setFocusable(false);

        try {
            zipFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        zipFormattedTextField.setToolTipText("Enter the address ZIP code.");
        zipFormattedTextField.setNextFocusableComponent(stateComboBox);
        zipFormattedTextField.setValue("");

        stateLabel.setLabelFor(stateComboBox);
        stateLabel.setText("State *:");
        stateLabel.setFocusable(false);

        stateComboBox.setModel(getStates());
        stateComboBox.setToolTipText("Select the state name.");
        stateComboBox.setNextFocusableComponent(cityComboBox);
        stateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateComboBoxActionPerformed(evt);
            }
        });

        cityLabel.setLabelFor(cityComboBox);
        cityLabel.setText("City *:");
        cityLabel.setFocusable(false);

        cityComboBox.setToolTipText("Enter the city name.");
        cityComboBox.setEnabled(false);
        cityComboBox.setNextFocusableComponent(emailTextField);
        cityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addressPanelLayout = new javax.swing.GroupLayout(addressPanel);
        addressPanel.setLayout(addressPanelLayout);
        addressPanelLayout.setHorizontalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zipLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(obsLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(streetLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addressPanelLayout.createSequentialGroup()
                        .addComponent(zipFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(obsTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(streetTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 136, Short.MAX_VALUE)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(areaLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(numberLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cityLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberFormattedTextField)
                    .addComponent(areaTextField)
                    .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        addressPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {areaTextField, cityComboBox, numberFormattedTextField, stateComboBox, zipFormattedTextField});

        addressPanelLayout.setVerticalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(streetLabel)
                    .addComponent(streetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberLabel)
                    .addComponent(numberFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(obsLabel)
                    .addComponent(obsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(areaLabel)
                    .addComponent(areaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(zipLabel)
                    .addComponent(zipFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stateLabel)
                    .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cityLabel)
                    .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        addressPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {areaLabel, areaTextField, cityComboBox, cityLabel, numberFormattedTextField, numberLabel, obsLabel, obsTextField, stateComboBox, stateLabel, streetLabel, streetTextField, zipFormattedTextField, zipLabel});

        picturePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Picture"));

        pictureLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pictureLabel.setToolTipText("Alter profile picture");
        pictureLabel.setRequestFocusEnabled(false);
        pictureLabel.setIcon(ImageUtil.getDefaultProfilePicture());
        pictureLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pictureLabelMouseClicked(evt);
            }
        });
        pictureLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pictureLabelKeyPressed(evt);
            }
        });

        pictureRemoveButton.setIcon(IconFontSwing.buildIcon(FontAwesome.TRASH, 15));
        pictureRemoveButton.setEnabled(false);
        pictureRemoveButton.setFocusable(false);
        pictureRemoveButton.setMaximumSize(new java.awt.Dimension(20, 20));
        pictureRemoveButton.setMinimumSize(new java.awt.Dimension(20, 20));
        pictureRemoveButton.setPreferredSize(new java.awt.Dimension(20, 20));
        pictureRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pictureRemoveButtonActionPerformed(evt);
            }
        });

        pictureEditButton.setIcon(IconFontSwing.buildIcon(FontAwesome.PENCIL, 15));
        pictureEditButton.setFocusable(false);
        pictureEditButton.setMaximumSize(new java.awt.Dimension(20, 20));
        pictureEditButton.setMinimumSize(new java.awt.Dimension(20, 20));
        pictureEditButton.setPreferredSize(new java.awt.Dimension(20, 20));
        pictureEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pictureEditButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout picturePanelLayout = new javax.swing.GroupLayout(picturePanel);
        picturePanel.setLayout(picturePanelLayout);
        picturePanelLayout.setHorizontalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picturePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(picturePanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(pictureEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pictureRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        picturePanelLayout.setVerticalGroup(
            picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(picturePanelLayout.createSequentialGroup()
                .addGroup(picturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pictureEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );

        socialNetworksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Social Networks"));

        facebookLabelIcon.setIcon(IconFontSwing.buildIcon(FontAwesome.FACEBOOK_OFFICIAL, 20));
        facebookLabelIcon.setLabelFor(facebookTextField);
        facebookLabelIcon.setFocusable(false);
        facebookLabelIcon.setMaximumSize(new java.awt.Dimension(21, 21));
        facebookLabelIcon.setMinimumSize(new java.awt.Dimension(21, 21));
        facebookLabelIcon.setPreferredSize(new java.awt.Dimension(21, 21));

        facebookLabel1.setText(":");
        facebookLabel1.setFocusable(false);

        facebookLabel.setText("https://facebook.com/");
        facebookLabel.setFocusable(false);

        facebookTextField.setNextFocusableComponent(instagramTextField);

        intagramLabelIcon.setIcon(IconFontSwing.buildIcon(FontAwesome.INSTAGRAM, 20));
        intagramLabelIcon.setLabelFor(instagramTextField);
        intagramLabelIcon.setFocusable(false);
        intagramLabelIcon.setMaximumSize(new java.awt.Dimension(21, 21));
        intagramLabelIcon.setMinimumSize(new java.awt.Dimension(21, 21));
        intagramLabelIcon.setPreferredSize(new java.awt.Dimension(21, 21));

        instagramLabel1.setText(":");
        instagramLabel1.setFocusable(false);

        instagramLabel.setText("https://instagram.com/");
        instagramLabel.setFocusable(false);

        instagramTextField.setNextFocusableComponent(pictureLabel);

        emailLabelIcon.setIcon(IconFontSwing.buildIcon(FontAwesome.AT, 20));
        emailLabelIcon.setLabelFor(facebookTextField);
        emailLabelIcon.setFocusable(false);
        emailLabelIcon.setMaximumSize(new java.awt.Dimension(21, 21));
        emailLabelIcon.setMinimumSize(new java.awt.Dimension(21, 21));
        emailLabelIcon.setPreferredSize(new java.awt.Dimension(21, 21));

        emailLabel.setText(":");
        emailLabel.setFocusable(false);

        emailTextField.setNextFocusableComponent(facebookTextField);

        javax.swing.GroupLayout socialNetworksPanelLayout = new javax.swing.GroupLayout(socialNetworksPanel);
        socialNetworksPanel.setLayout(socialNetworksPanelLayout);
        socialNetworksPanelLayout.setHorizontalGroup(
            socialNetworksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(socialNetworksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(socialNetworksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(emailLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facebookLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(intagramLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(socialNetworksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(emailLabel)
                    .addComponent(facebookLabel1)
                    .addComponent(instagramLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(socialNetworksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(socialNetworksPanelLayout.createSequentialGroup()
                        .addComponent(facebookLabel)
                        .addGap(0, 0, 0)
                        .addComponent(facebookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(socialNetworksPanelLayout.createSequentialGroup()
                        .addComponent(instagramLabel)
                        .addGap(0, 0, 0)
                        .addComponent(instagramTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        socialNetworksPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {facebookTextField, instagramTextField});

        socialNetworksPanelLayout.setVerticalGroup(
            socialNetworksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, socialNetworksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(socialNetworksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(emailLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(socialNetworksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(facebookLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(facebookLabel1)
                    .addComponent(facebookLabel)
                    .addComponent(facebookTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(socialNetworksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(intagramLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(instagramLabel1)
                    .addComponent(instagramLabel)
                    .addComponent(instagramTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        socialNetworksPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {emailTextField, facebookLabel, facebookLabel1, facebookLabelIcon, facebookTextField, instagramLabel, instagramLabel1, instagramTextField, intagramLabelIcon});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(socialNetworksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(personPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(personPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picturePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(socialNetworksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {personPanel, picturePanel});

    }// </editor-fold>//GEN-END:initComponents

    private void pictureLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pictureLabelMouseClicked
        editProfilePicture();
    }//GEN-LAST:event_pictureLabelMouseClicked

    private void pictureLabelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pictureLabelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {            
            editProfilePicture();
        }
    }//GEN-LAST:event_pictureLabelKeyPressed

    private void stateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateComboBoxActionPerformed
        String state = (String) stateComboBox.getSelectedItem();
        if (!AddressUtil.isValid(state)) {
            stateComboBox.setSelectedItem("");
            cityComboBox.setSelectedItem("");
            cityComboBox.setEnabled(false);
            return;
        }
        cityComboBox.setModel(getCities(state));
        cityComboBox.setSelectedItem("");
        cityComboBox.setEnabled(true);
        parent.updateWizardButtons();
    }//GEN-LAST:event_stateComboBoxActionPerformed

    private void genderFemaleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderFemaleRadioButtonActionPerformed
        parent.updateWizardButtons();
    }//GEN-LAST:event_genderFemaleRadioButtonActionPerformed

    private void genderMaleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderMaleRadioButtonActionPerformed
        parent.updateWizardButtons();
    }//GEN-LAST:event_genderMaleRadioButtonActionPerformed

    private void cityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityComboBoxActionPerformed
        parent.updateWizardButtons();
    }//GEN-LAST:event_cityComboBoxActionPerformed

    private void pictureRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pictureRemoveButtonActionPerformed
        removeProfilePicture();
    }//GEN-LAST:event_pictureRemoveButtonActionPerformed

    private void pictureEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pictureEditButtonActionPerformed
        editProfilePicture();
    }//GEN-LAST:event_pictureEditButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addressPanel;
    private javax.swing.JLabel areaLabel;
    private javax.swing.JTextField areaTextField;
    private javax.swing.JComboBox cityComboBox;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JFormattedTextField cpfFormattedTextField;
    private javax.swing.JLabel cpfLabel;
    private javax.swing.JFormattedTextField dobFormattedTextField;
    private javax.swing.JLabel dobLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabelIcon;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel facebookLabel;
    private javax.swing.JLabel facebookLabel1;
    private javax.swing.JLabel facebookLabelIcon;
    private javax.swing.JTextField facebookTextField;
    private javax.swing.ButtonGroup genderButtonGroup;
    private javax.swing.JRadioButton genderFemaleRadioButton;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JRadioButton genderMaleRadioButton;
    private javax.swing.JPanel genderPanel;
    private javax.swing.JLabel instagramLabel;
    private javax.swing.JLabel instagramLabel1;
    private javax.swing.JTextField instagramTextField;
    private javax.swing.JLabel intagramLabelIcon;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JFormattedTextField numberFormattedTextField;
    private javax.swing.JLabel numberLabel;
    private javax.swing.JLabel obsLabel;
    private javax.swing.JTextField obsTextField;
    private javax.swing.JLabel occupationLabel;
    private javax.swing.JTextField occupationTextField;
    private javax.swing.JPanel personPanel;
    private javax.swing.JFormattedTextField phoneFormattedTextField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JButton pictureEditButton;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JPanel picturePanel;
    private javax.swing.JButton pictureRemoveButton;
    private javax.swing.JLabel rgLabel;
    private javax.swing.JTextField rgTextField;
    private javax.swing.JPanel socialNetworksPanel;
    private javax.swing.JComboBox stateComboBox;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JLabel streetLabel;
    private javax.swing.JTextField streetTextField;
    private javax.swing.JCheckBox whatsappCheckBox;
    private javax.swing.JLabel whatsappLabel;
    private javax.swing.JLabel whatsappLabelIcon;
    private javax.swing.JFormattedTextField zipFormattedTextField;
    private javax.swing.JLabel zipLabel;
    // End of variables declaration//GEN-END:variables
    
}
