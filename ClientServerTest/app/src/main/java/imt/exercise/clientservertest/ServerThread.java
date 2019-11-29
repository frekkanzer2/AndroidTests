package imt.exercise.clientservertest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    private ServerSocket myServer = null;
    private int SERVERPORT = 5000;

    public ServerThread(){

    }

    public void closeServer(){
        try{
            myServer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        super.run();
        try{
            myServer = new ServerSocket(SERVERPORT);
        }catch(IOException e){
            System.err.println("********************************************");
            e.printStackTrace();
            System.err.println("Problem creating ServerSocket");
        }
        System.err.println("Server is waiting for connection");
        MainActivity.changeStatusServer("Server is waiting for connection");
        Socket clientConnection = null;
        try{
            clientConnection = myServer.accept();
        }catch(IOException e){
            System.err.println("********************************************");
            e.printStackTrace();
            System.err.println("Problem accepting client connection");
        }

        if (clientConnection != null) {
            System.err.println("Accepted connection");
            MainActivity.changeStatusServer("Accepted connection");
        }
        try{
            clientConnection.close();
            myServer.close();
        }catch(IOException e){
            System.err.println("********************************************");
            e.printStackTrace();
            System.err.println("Problem closing client and server");
        }

    }

}
