package imt.exercise.xmastombola.multiplayer.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import imt.exercise.xmastombola.multiplayer.CardMp;
import imt.exercise.xmastombola.multiplayer.MpCardsActivity;

public class ClientThread extends Thread {

    private boolean clientIsConnected = false;
    private boolean threadAlive = true;
    private int PORT;
    private Socket connection = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private ThreadWaitingNumbers receiver = null;
    private ArrayList<CardMp> arrayOfCards = null;

    public ClientThread(int port, ArrayList<CardMp> cards){
        this.PORT = port;
        this.arrayOfCards = cards;
    }

    public void run(){
        super.run();
        while (!clientIsConnected && threadAlive){
            System.err.println("Trying connection to localhost");
            try {
                connection = new Socket("10.0.2.2", PORT);
            } catch (IOException e) {
                System.err.println("CANNOT CONNECT TO THE HOST");
            }
            if (connection != null) {
                clientIsConnected = true;
                System.err.println("Socket connected");
            }
        }
        if (clientIsConnected && threadAlive){
            System.err.println("Connected successfully to localhost 10.0.2.2");
            try{
                this.out = new ObjectOutputStream(connection.getOutputStream());
                this.in = new ObjectInputStream(connection.getInputStream());
            }catch(IOException e){
                System.err.println("Cannot create streams!");
                System.err.println("There will be some errors because the client will not receive numbers!");
            }
            System.err.println("Client created successfully");
            boolean flag = startReceiveNumbers();
            if (flag) {
                System.err.println("Client is listening for numbers");
                MpCardsActivity.serverStatus_setText("Connected");
            }
            else {
                System.err.println("ERROR: Client is not listening for numbers");
                closeConnection();
                System.err.println("Connection closed for a listening problem");
            }
        }
        if (threadAlive) keepAlive();
    }

    public void closeConnection(){
        try{
            if (this.receiver != null)
                this.receiver.stopReceiving();
            if (this.connection != null)
                this.connection.close();
            if (this.out != null)
                this.out.close();
            if (this.in != null)
                this.in.close();
            this.threadAlive = false;
        } catch(IOException e){
            System.err.println("Problem closing client connection");
        }
    }

    private boolean startReceiveNumbers(){
        receiver = new ThreadWaitingNumbers(connection, out, in, arrayOfCards);
        receiver.start();
        return receiver.getStatus();
    }

    private void keepAlive(){}

}
