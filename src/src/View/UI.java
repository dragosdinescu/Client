package src.View;

import src.Client;
import src.Constants.Action;
import src.Message;
import src.Model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class UI {
    private GraphicsConfiguration gc;
    //private TextArea textarea;
    private JFrame frame;
    private JLabel firstName, lastName, email, phoneNumber, carrier, registrationDate, select;
    private JTextField firstNameField, lastNameField, emailField, phoneNumberField, carrierField, registrationDateField;
    private JPanel buttonPanel, fieldsPanel, comboxPanel;
    private JButton submit, add, delete, modify, clear;
    private HashMap<String, Contact> contactMap;
    private JComboBox<String> cb;
    public ArrayList<JTextField> textFields;
    public Client client;
    public UI (HashMap<String, Contact> contactMap){
        this.contactMap = contactMap;
        textFields = new ArrayList<>();
    }
    public Contact getSelectedContact() {
        Contact contact = contactMap.get(cb.getSelectedItem());
        return contact;
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
        comboxPanel.setLayout(new BoxLayout(comboxPanel, BoxLayout.Y_AXIS));
        clear.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(2, 3));
        buttonPanel.setLayout(new GridLayout(4, 1));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setActionListeners (){
        submit.addActionListener(actionEvent-> {
            Contact contact = getSelectedContact();
            Message message = new Message(contact, Action.VERIFY, contact.getFirstName());
            client.sendObject(message);
        });

        add.addActionListener(actionEvent-> addContact());

        delete.addActionListener(actionEvent-> {
            Contact contact = getSelectedContact();
            Message message = new Message(contact, Action.REMOVE, contact.getFirstName());
            client.sendObject(message);
            deleteContact();
        });

        modify.addActionListener(actionEvent-> {
            Contact contact = modifyContact();
            Message message =  new Message(contact, Action.MODIFY, contact.getFirstName());
            client.sendObject(message);
        });

        clear.addActionListener(actionEvent -> clearFields());

        cb.addActionListener( actionEvent -> {
            String id = (String) cb.getSelectedItem();
            Contact selectedContact = contactMap.get(id);
            System.out.println(selectedContact);
            if(selectedContact!=null) {
                fillFields(selectedContact);
            }
        });
    }

    private void createButtons (){
        submit = new JButton("Trimite");
        add = new JButton("Contact nou");
        delete = new JButton("Sterge");
        modify = new JButton("Modifica");
        clear = new JButton("Clear");
    }

    private void fillFields(Contact contact){
        this.firstNameField.setText(contact.getFirstName());
        this.lastNameField.setText(contact.getLastName());
        this.emailField.setText(contact.getEmail());
        this.phoneNumberField.setText(contact.getPhoneNumber());
        this.registrationDateField.setText(String.valueOf(contact.getDate()));
        this.carrierField.setText(contact.getCarrierEnum());
    }

    private void createElements(){
        buttonPanel = new JPanel();
        fieldsPanel = new JPanel();
        comboxPanel = new JPanel();
        frame = new JFrame("Client");

        select = new JLabel("Selecteaza ID");
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
        cb = new JComboBox<String>(contactMap.keySet().toArray(new String[0]));
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
        comboxPanel.add(clear);
        buttonPanel.add(submit);
        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(modify);
        frame.add(fieldsPanel);
        frame.add(buttonPanel);
        frame.add(comboxPanel);
    }
    private void deleteContact() {
        String id = (String) cb.getSelectedItem();
        contactMap.remove(id);
        updateComboBox();
    }
    private void clearFields(){
        for (JTextField element : textFields) {
            element.setText("");
        }
    }
    public void updateComboBox(){
        cb.removeAllItems();
        Set<String> keySet = contactMap.keySet();
        for (String id:keySet) {
            cb.addItem(id);
        }
    }
    private void addContact(){
        DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(registrationDateField.getText());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Contact contact = new Contact(firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneNumberField.getText(),carrierField.getText(), startDate );
        contactMap.put(firstNameField.getText(), contact);
        updateComboBox();
        clearFields();

    }
    private Contact modifyContact(){
        String id = (String) cb.getSelectedItem();
        Contact existingcontact = contactMap.get(id);
        existingcontact.setFirstName(firstNameField.getText());
        existingcontact.setLastName(lastNameField.getText());
        existingcontact.setEmail(emailField.getText());
        existingcontact.setPhoneNumber(phoneNumberField.getText());
        existingcontact.setCarrierEnum(carrierField.getText());

        DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date startDate = null;
        try {
            startDate = df.parse(registrationDateField.getText());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        existingcontact.setDate(startDate);
        clearFields();
        return existingcontact;
    }


}




