package src.Server;

import src.Constants.Action;
import src.Message;
import src.Model.CarrierEnum;
import src.Model.Contact;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Server {
    private Thread thread;
    private Socket socket;
    private ServerSocket serverSocket;
    private int port;
    private HashMap<String, Contact> contactMap;
    private CarrierEnum carrierEnum;
    private HashMap<Socket, ClientWrapper> clientList;
    final String dateFormat = "dd-mm-yyyy";
    SimpleDateFormat sdf;

    public Server(int port){
        clientList = new HashMap<>();
        sdf = new SimpleDateFormat(dateFormat);
        carrierEnum = new CarrierEnum("Digi", "vodafone", "Orange");
        contactMap = new HashMap<>();
        this.port = port;//atribuire
        createDummyContacts();
        try { //prinde eroarea
            serverSocket = new ServerSocket(this.port);//instantiere
        } catch (IOException e) {
            e.printStackTrace();//printeaza eroare
        }
        startServer();
    }

    public void startServer(){//asculta dupa conexiuni noi, cand gaseste ii atribuie un theread nou
        System.out.println("running server");
        while(true) try{
            socket = serverSocket.accept();//accepta conexiunea
            ClientWrapper clientWrapper = new ClientWrapper(socket);
            clientList.put(socket, clientWrapper);
            System.out.println(" acquiered new client " + socket.getLocalPort());
            thread = new Thread(){
                @Override
                public void run() {
                    readObject(clientWrapper);//este infinit. PASEZ VARIABILA CLIENTWRAPPER
                    // Daca iese din metoda, inchide thread ul
                    this.interrupt();
                }
            };
            thread.start();//executa functia de run
            sendInitialData(clientWrapper);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void broadcast(Message message){
        for (Socket socket:clientList.keySet()) {
            sendObject( clientList.get(socket), message);
        }
    }
    public void broadcastAllButSender(Message message, ClientWrapper clientWrapper){
        for (Socket socket:clientList.keySet()) {
            if(!clientWrapper.equals(clientList.get(socket))){
                sendObject( clientList.get(socket), message);
            }
        }


    }
    public void sendInitialData(ClientWrapper clientWrapper){

        for (String key: contactMap.keySet()) {
            Contact contact = contactMap.get(key);
            Message message = new Message(contact, Action.ADD, contact.getFirstName());
            sendObject(clientWrapper, message);
        }

    }
    public void createDummyContacts(){
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
            contactMap.put(c1.getFirstName(), c1);
            contactMap.put(c2.getFirstName(), c2);
            contactMap.put(c3.getFirstName(), c3);

    }
    public void readObject(ClientWrapper clientWrapper){
        Socket socket = clientWrapper.getSocket();//returneaza instanta de getsocket DIN CLIENTWRAPPER
        while( ! socket.isClosed()){
            try{
                Object receivedObject = clientWrapper.getObjectInputStream().readObject();//receivedObject returneaza OBIECTUL primit de la client
                respond(clientWrapper,(Message) receivedObject);
            }catch(Exception e){
                e.printStackTrace();
                try {
                    socket.close();/// inchide socketul si iasa din while
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void respond(ClientWrapper clientWrapper, Message message){
        Contact contact = message.contact;
        if(message.action.equals(Action.VERIFY)){
            if(contactMap.containsKey(message.ID)){
                Message response = new Message(contact, Action.FAIL, message.ID);
                sendObject(clientWrapper, response);
            }else{
                contactMap.put(message.ID, contact);
                Message response = new Message(contact, Action.SUCCESS, message.ID);
                sendObject(clientWrapper, response);
                Message adding = new Message(contact, Action.ADD, message.ID);
                broadcastAllButSender(adding, clientWrapper);
            }
        }
        if(message.action.equals(Action.REMOVE)){
            contactMap.remove(message.ID);
            Message removeContact = new Message(contact, Action.REMOVE, message.ID);
            broadcastAllButSender(removeContact, clientWrapper);
        }
        if(message.action.equals(Action.MODIFY)){
            contactMap.remove(message.ID);
            contactMap.put(message.ID, contact);
            Message modify = new Message(contact, Action.MODIFY, message.ID);
            broadcastAllButSender(modify,clientWrapper);
        }
    }
    public void sendObject(ClientWrapper clientWrapper, Object object){
        Socket socket = clientWrapper.getSocket();//primeste instanta de socket din clientwrapper
        if(socket==null||socket.isClosed()){ //daca socketul e null sau inchis, iasa din metoda cu RETURN
            System.out.println("cannot send to a closed socket");
            return;
        }
        ObjectOutputStream objectOutputStream = clientWrapper.getObjectOutputStream();
        try {
            objectOutputStream.writeObject(object);//trimite obiectul din argument catre client
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ClientWrapper{
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ClientWrapper(Socket socket){
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.flush(); //curata streamul conform documentatiei
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getObjectInputStream() {//returneaza instanta de objInput din instanta clientwrapper
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }
}
