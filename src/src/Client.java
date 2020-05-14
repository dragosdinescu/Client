package src;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client  {

    private Socket socket;
    private Thread thread;
    private int port;
    private boolean foundHost = false;
    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;



    public Client(int port){
        this.port = port;
        connectAsClient(this.port);
    }



    private void connectAsClient(Integer port){
        while (!this.foundHost) {// cat timp nu a gasit server, incearca sa se conecteze pana gaseste
            try {
                socket = new Socket("127.0.0.1", port);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.flush();
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                foundHost = true;
                System.out.println("CONNECTED TO SERVER");
            } catch (Exception e) {
                System.out.println("Couldnt connect to server...");
                foundHost = false;
            }
        }
        thread = new Thread(){
            @Override
            public void run() {
                receiveObject();
                this.interrupt();
            }
        };
        thread.start();
    }

    public void receiveObject() {
        while (foundHost) {
            try {
                Object o = objectInputStream.readObject();//variabila "o" returneaza obiectul de pe inputstream care a fost trimis de server
                handle((String) o);
            } catch (Exception e){
                foundHost=false;
                e.printStackTrace();
            }
        }
    }

    public void handle(String tokens){

                System.out.println(tokens);
        }


}



