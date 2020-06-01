package src;

import src.Model.Contact;
import src.View.UI;
import java.io.*;
import java.util.HashMap;

public class MainUI {

    public static void main(String[] args) throws IOException {

        HashMap< String, Contact> contactMap = new HashMap<>();

        UI ui = new UI(contactMap);
        ui.startUI();

        Client client = new Client(5000, contactMap, ui);

        ui.client = client;
    }

    }
