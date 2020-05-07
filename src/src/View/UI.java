package src.View;

import javax.swing.*;
import java.awt.*;

public class UI {
    private GraphicsConfiguration gc;
    private JFrame frame;
    private JLabel firstName, lastName, email, phoneNumber, carrier, registrationDate;
    private JTextField firstNameField, lastNameField, emailField, phoneNumberField, carrierField, registrationDateField;
    private JPanel buttonPanel, fieldsPanel;
    private JButton submit, add, delete, modify;

    public void startUI(){
        this.frame = new JFrame(gc);
        this.frame.setTitle("Client");
        this.frame.setSize(600, 600);
        this.frame.setLocation(200, 200);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);


        frame =new JFrame("Client");
        buttonPanel = new JPanel();
        buttonPanel.setBounds(30,30,30,20);
        fieldsPanel = new JPanel();
        firstName = new JLabel("Nume");
        lastName = new JLabel("Prenume");
        email = new JLabel("Email");
        phoneNumber = new JLabel("Telefon");
        carrier = new JLabel("Retea");
        registrationDate = new JLabel("Data");
        firstNameField = new JTextField(" ");
        firstNameField.setSize(20,20);
        lastNameField = new JTextField(" ");
        emailField = new JTextField(" ");
        phoneNumberField = new JTextField(" ");
        carrierField = new JTextField(" ");
        registrationDateField = new JTextField(" ");
        submit = new JButton("Trimite");
        add = new JButton("Contct nou");
        delete = new JButton("Sterge");
        modify = new JButton("Modifica");

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

        buttonPanel.add(submit);
        buttonPanel.add(add);
        buttonPanel.add(delete);
        buttonPanel.add(modify);

        frame.setSize(500,300);
        frame.add(fieldsPanel, BorderLayout.PAGE_START);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }



}



