package imt.exercise.xmastombola.multiplayer.threads;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import imt.exercise.xmastombola.multiplayer.BoardServer;

public class ThreadEmitter extends Thread {

    private Socket connection;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public ThreadEmitter(Socket connection){
        this.connection = connection;
        streamsCreation();
    }

    public void run(){
        super.run();
    }

    //sendNumberToClient sends a number to the connected client
    public void sendNumberToClient(int number){
        try {
        	out.writeObject(number);
        } catch (Exception e) {
        	System.err.println("Cannot send the number.");
        	e.printStackTrace();
        	closeConnection();
        }
    }

    //streamsCreation creates input and output streams with the socket
    private void streamsCreation(){
        try{
            this.out = new ObjectOutputStream(connection.getOutputStream());
            this.in = new ObjectInputStream(connection.getInputStream());
        } catch (Exception e){
            System.err.println("Problem with streams creation.");
            e.printStackTrace();
            closeConnection();
        }
    }

    //closeConnection closes connection with the socket
    private void closeConnection(){
        try {
            this.connection.close();
        } catch (Exception e_){
            e_.printStackTrace();
        }
    }

}
