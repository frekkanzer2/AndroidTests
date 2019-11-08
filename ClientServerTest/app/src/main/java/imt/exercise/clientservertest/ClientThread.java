package imt.exercise.clientservertest;

import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket connection = null;

    public ClientThread(){

    }

    public void run(){
        super.run();
        try{
            System.err.println("Connecting...");
            MainActivity.changeStatusClient("Connecting to server...");
            connection = new Socket("10.0.2.2", 9000);
            MainActivity.changeStatusClient("Client connected");
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
