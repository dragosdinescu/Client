package src.Server;

import src.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Thread thread;
    private Socket socket;
    private ServerSocket serverSocket;
    private int port;
    public Server(int port){
        this.port = port;//atribuire
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
    public void sendInitialData(ClientWrapper clientWrapper){
        sendObject(clientWrapper, "server says");
    }
    public void readObject(ClientWrapper clientWrapper){
        Socket socket = clientWrapper.getSocket();//returneaza instanta de getsocket DIN CLIENTWRAPPER
        while( ! socket.isClosed()){//cat timp socketul nu e inchis, intra in try
            try{
                Object receivedObject = clientWrapper.getObjectInputStream().readObject();//receivedObject returneaza OBIECTUL primit de la client
                respond(clientWrapper, receivedObject);
            }catch(Exception e){
                e.printStackTrace();
                try {
                    socket.close();/// inchide socketul si iasa din while si din functie pt ca nu indeplinseste conditia,
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public void respond(ClientWrapper clientWrapper, Object receivedObject){
        System.out.println("received object " + receivedObject);

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
            objectOutputStream.flush();//curata
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
