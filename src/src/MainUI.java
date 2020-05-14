package src;

import src.Model.CarrierEnum;
import src.Model.Contact;
import src.View.UI;
import java.net.*;
import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MainUI {

    public static void main(String[] args) throws IOException {
        final String dateFormat = "dd-mm-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        CarrierEnum carrierEnum = new CarrierEnum("Digi", "vodafone", "Orange");
        Contact c1 = null;
        Contact c2 = null;
        Contact c3 = null;
        try {
            c1 = new Contact("Radu", "Soro", "ceva@ceva.com", "077", carrierEnum.getDigi(), sdf.parse("01-12-2020"));
            c2 = new Contact("Ade", "Mirea", "someting@ceva.com", "077", carrierEnum.getDigi(), sdf.parse("01-12-2020"));
            c3 = new Contact("Ion", "Popescu", "whatever@ceva.com", "077", carrierEnum.getDigi(), sdf.parse("01-12-2020"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        HashMap<String, Contact> contactMap = new HashMap<>();
        contactMap.put("01", c1);
        contactMap.put("02", c2);
        contactMap.put("03", c3);


        UI ui = new UI(contactMap);
        ui.startUI();

        Client client = new Client(5000);
    }

    }
