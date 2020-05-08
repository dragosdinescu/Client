package src.View;

import src.Model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class UI {
    private GraphicsConfiguration gc;
    private JFrame frame;
    private JLabel firstName, lastName, email, phoneNumber, carrier, registrationDate, select;
    private JTextField firstNameField, lastNameField, emailField, phoneNumberField, carrierField, registrationDateField;
    private JPanel buttonPanel, fieldsPanel, comboxPanel;
    private JButton submit, add, delete, modify, ok;
    private HashMap<String, Contact> contactMap;

    public UI (HashMap<String, Contact> contactMap){
        this.contactMap = contactMap;
    }

    public void startUI() {
        this.frame = new JFrame(gc);
        this.frame.setTitle("Client");
        this.frame.setSize(1000, 1000);
        this.frame.setLocation(200, 200);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);


        frame = new JFrame("Client");
        buttonPanel = new JPanel();
        fieldsPanel = new JPanel();
        comboxPanel = new JPanel();
        comboxPanel.setLayout(new BoxLayout(comboxPanel, BoxLayout.Y_AXIS));
        select = new JLabel("Selecteaza");
        firstName = new JLabel("Nume");
        lastName = new JLabel("Prenume");
        email = new JLabel("Email");
        phoneNumber = new JLabel("Telefon");
        carrier = new JLabel("Retea");
        registrationDate = new JLabel("Data");
        firstNameField = new JTextField(" ");
        lastNameField = new JTextField(" ");
        emailField = new JTextField(" ");
        phoneNumberField = new JTextField(" ");
        carrierField = new JTextField(" ");
        registrationDateField = new JTextField(" ");
        submit = new JButton("Trimite");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Contactul a fost trimis");
            }
        });
        add = new JButton("Contct nou");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        delete = new JButton("Sterge");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        modify = new JButton("Modifica");
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setLayout(new FlowLayout());

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
        select.setAlignmentX(Component.CENTER_ALIGNMENT);
        String[] choices = contactMap.keySet().toArray(new String[0]);
        JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        cb.setVisible(true);
        ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) cb.getSelectedItem();
                String message = "";
                Contact contact = contactMap.get(id);
                message += contact.getFirstName()+"\n"
                        +contact.getLastName()+"\n"
                        +contact.getEmail()+"\n"
                        +contact.getPhoneNumber()+"\n"
                        +contact.getCarrierEnum()+"\n"
                        +contact.getDate()+"\n";
                JOptionPane.showMessageDialog(null,message);
            }
        });

        comboxPanel.add(cb);
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboxPanel.add(ok);

        buttonPanel.add(submit);
        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(modify);

        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(2, 3));
        buttonPanel.setLayout(new GridLayout(4, 1));
        frame.add(fieldsPanel);
        frame.add(buttonPanel);
        frame.add(comboxPanel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


}




