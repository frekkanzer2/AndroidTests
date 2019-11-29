package imt.exercise.clientservertest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket connection = null;
    private int CLIENTPORT = 6000;

    public ClientThread(){

    }

    public void run(){
        super.run();
        try{
            System.err.println("Connecting...");
            MainActivity.changeStatusClient("Connecting to server...");
            connection = new Socket(InetAddress.getByName ("10.0.2.2"), CLIENTPORT);
            MainActivity.changeStatusClient("Client connected");
            System.err.println(connection.getInetAddress().toString());
            System.err.println(connection.getLocalPort());
            System.err.println("CONNECTED");
        }catch(IOException e){
            System.err.println("********************************************");
            System.err.println(e.getMessage());
            System.err.println("********************************************");
            e.printStackTrace();
            System.err.println("********************************************");
            System.err.println("ERR > Problem with connection");
        }
    }

}
