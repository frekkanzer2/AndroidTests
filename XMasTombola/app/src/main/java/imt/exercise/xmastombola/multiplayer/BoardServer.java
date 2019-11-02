package imt.exercise.xmastombola.multiplayer;

import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import imt.exercise.xmastombola.multiplayer.threads.ThreadEmitter;

public class BoardServer {

	private ServerSocket myServer = null;
	private ArrayList<ThreadEmitter> allThreads = null;
	private boolean canAccept = true;

	//BoardServer constructor creates a new host in the local network
		//It sets a custom port and a limit of players
	public BoardServer(int port, int maxPlayers) {
		try {
			myServer = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			if (myServer == null) System.err.println("SERVER NOT CREATED");
			System.exit(0);
		}
		allThreads = new ArrayList<ThreadEmitter>(maxPlayers);
	}

	//execute() accepts connections from clients, creating for each one a thread
		//this method will run while the server can accept connections
		//turn off "accept connections" with turnOffAccept() method
	public void execute() {
		while(this.canAccept) {
			Socket newestSocket = null;
			try {
				// for each client, it will create a new thread that
				// contains the socket for communication
				System.err.println("I'll wait new connections");
				newestSocket = myServer.accept();
				System.err.println("Connection accepted");
				ThreadEmitter tempThread = new ThreadEmitter(newestSocket);
				allThreads.add(tempThread);
				tempThread.start();
				BoardActivity.setNoPlayers(BoardActivity.getNoPlayers() + 1);
				BoardActivity.refreshNoPlayers();
			} catch (IOException e){
				System.err.println("Connection acceptation interrupted");
				e.printStackTrace();
			}
		}
		System.err.println("Server doesn't accept connections anymore");
	}

	//turnOffAccept() turns off connection acceptations
	public void turnOffAccept(){
		this.canAccept = false;
	}

	//sendNumber() sends a number to all clients
	public void sendNumber(int number){
		if (!this.canAccept)
			for (int i = 0; i < this.allThreads.size(); i++)
				this.allThreads.get(i).sendNumberToClient(number);
	}

	//closeServer() closes all client connections and the principal server connection
	public void closeServer(){
		this.canAccept = false;
		for (int i = 0; i < this.allThreads.size(); i++)
			this.allThreads.get(i).closeConnection();
		try{
			this.myServer.close();
		} catch (IOException e){
			e.printStackTrace();
			System.err.println("Error while closing the server");
			System.exit(0);
		}
	}
	
}
