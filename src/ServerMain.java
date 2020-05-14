import src.Server.Server;

import java.net.*;
import java.io.*;

public class ServerMain {
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    // constructor with port

    public static void main(String[] args) throws IOException {

        Server server =new Server(5000);
    }



}
