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


//        Socket socket = new Socket("127.0.0.1",5000);

        class Client {
            // initialize socket and input output streams
            private Socket socket = null;
            private DataInputStream input = null;
            private DataOutputStream out = null;

            // constructor to put ip address and port
            public Client(String address, int port) {
                // establish a connection
                try {
                    socket = new Socket(address, port);
                    System.out.println("Connected");

                    // takes input from terminal
                    input = new DataInputStream(System.in);

                    // sends output to the socket
                    out = new DataOutputStream(socket.getOutputStream());
                } catch (UnknownHostException u) {
                    System.out.println(u);
                } catch (IOException i) {
                    System.out.println(i);
                }

                // string to read message from input
                String line = "";

                // keep reading until "Over" is input
                while (!line.equals("Over")) {
                    try {
                        line = input.readLine();
                        out.writeUTF(line);
                    } catch (IOException i) {
                        System.out.println(i);
                    }
                }

                // close the connection
                try {
                    input.close();
                    out.close();
                    socket.close();
                } catch (IOException i) {
                    System.out.println(i);
                }
                Client client = new Client("127.0.0.1", 5000);
            }



        }

    }
}
