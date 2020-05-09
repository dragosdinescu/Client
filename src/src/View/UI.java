package src.View;

import src.Model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class UI {
    private GraphicsConfiguration gc;
    private JFrame frame;
    private JLabel firstName, lastName, email, phoneNumber, carrier, registrationDate, select;
    private JTextField firstNameField, lastNameField, emailField, phoneNumberField, carrierField, registrationDateField;
    private JPanel buttonPanel, fieldsPanel, comboxPanel;
    private JButton submit, add, delete, modify, ok;
    private HashMap<String, Contact> contactMap;
    private JComboBox<String> cb;
    public ArrayList<JTextField> textFields;
    public UI (HashMap<String, Contact> contactMap){
        this.contactMap = contactMap;
        textFields = new ArrayList<>();
    }

    public void startUI() {
        this.frame = new JFrame(gc);
        this.frame.setTitle("Client");
        this.frame.setSize(1000, 1000);
        this.frame.setLocation(200, 200);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        createButtons();
        createElements();
        setActionListeners();
        addElements();
        setSettings();
    }

    private void setSettings (){
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new FlowLayout());
        select.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.setVisible(true);
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(2, 3));
        buttonPanel.setLayout(new GridLayout(4, 1));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setActionListeners (){
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Contactul a fost trimis");
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ok.addActionListener(actionEvent -> clearFields());
        cb.addActionListener( actionEvent -> {
            String id = (String) cb.getSelectedItem();
            Contact contact = contactMap.get(id);
            fillFields(contact);
        });
    }

    private void createButtons (){
        submit = new JButton("Trimite");
        add = new JButton("Contact nou");
        delete = new JButton("Sterge");
        modify = new JButton("Modifica");
        ok = new JButton("Clear");
    }

    private void fillFields(Contact contact){
        this.firstNameField.setText(contact.getFirstName());
        this.lastNameField.setText(contact.getLastName());
        this.emailField.setText(contact.getEmail());
        this.phoneNumberField.setText(contact.getPhoneNumber());
        this.registrationDateField.setText(String.valueOf(contact.getDate()));
        this.carrierField.setText(contact.getCarrierEnum());
    }

    private void clearFields(){
        for (int i = 0; i <textFields.size() ; i++) {
            JTextField element = textFields.get(i);
            element.setText("");
        }
    }

    private void createElements(){
        buttonPanel = new JPanel();
        fieldsPanel = new JPanel();
        comboxPanel = new JPanel();
        frame = new JFrame("Client");
        comboxPanel.setLayout(new BoxLayout(comboxPanel, BoxLayout.Y_AXIS));
        select = new JLabel("Selecteaza");
        firstName = new JLabel("Nume");
        lastName = new JLabel("Prenume");
        email = new JLabel("Email");
        phoneNumber = new JLabel("Telefon");
        carrier = new JLabel("Retea");
        registrationDate = new JLabel("Data");
        firstNameField = new JTextField(" ");
        textFields.add(firstNameField);
        lastNameField = new JTextField(" ");
        textFields.add(lastNameField);
        emailField = new JTextField(" ");
        textFields.add(emailField);
        phoneNumberField = new JTextField(" ");
        textFields.add(phoneNumberField);
        carrierField = new JTextField(" ");
        textFields.add(carrierField);
        registrationDateField = new JTextField(" ");
        textFields.add(registrationDateField);
        cb = new JComboBox<String>( contactMap.keySet().toArray(new String[0]));
    }

    private void addElements(){
        fieldsPanel.add(firstName);
        fieldsPanel.add(firstNameField);
        fieldsPanel.add(lastName);
        fieldsPanel.add(lastNameField);
        fieldsPanel.add(email);
        fieldsPanel.add(emailField);
        fieldsPanel.add(phoneNumber);
        fieldsPanel.add(phoneNumberField);
        fieldsPanel.add(carrier);
        fieldsPanel.add(carrierField);
        fieldsPanel.add(registrationDate);
        fieldsPanel.add(registrationDateField);
        comboxPanel.add(select);
        comboxPanel.add(cb);
        comboxPanel.add(ok);
        buttonPanel.add(submit);
        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(modify);
        frame.add(fieldsPanel);
        frame.add(buttonPanel);
        frame.add(comboxPanel);
    }

}




