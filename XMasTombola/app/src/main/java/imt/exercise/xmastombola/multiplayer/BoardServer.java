package imt.exercise.xmastombola.multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import imt.exercise.xmastombola.multiplayer.threads.ThreadEmitter;

public class BoardServer {

	private ServerSocket myServer = null;
	private ArrayList<ThreadEmitter> allThreads = null;
	private boolean canAccept = true;
	
	public BoardServer(int port, int maxPlayers) {
		try {
			myServer = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		allThreads = new ArrayList<ThreadEmitter>(maxPlayers);
	}
	
	public void execute() {
		int index = 0;
		while(this.canAccept) {
			Socket newestSocket = null;
			try {
				// for each client, it will create a new thread that
				// contains the socket for communication
				newestSocket = myServer.accept();
				ThreadEmitter tempThread = new ThreadEmitter(newestSocket);
				allThreads.add(tempThread);
				tempThread.start();
			} catch (IOException e){
				e.printStackTrace();
				System.err.println("Problem with connection.");
			}
		}
		System.err.println("Server doesn't accept connections anymore");
	}

	public void turnOffAccept(){
		this.canAccept = false;
	}

	public void sendNumber(int number){
		for (int i = 0; i < this.allThreads.size(); i++){
			this.allThreads.get(i).sendNumberToClient(number);
		}
	}
	
}
