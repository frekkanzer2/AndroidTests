package imt.exercise.xmastombola.multiplayer.threads;

import imt.exercise.xmastombola.multiplayer.BoardServer;

public class ThreadAcceptConnections extends Thread {

    private BoardServer hook_server = null;

    public ThreadAcceptConnections(BoardServer server){
        this.hook_server = server;
    }

    @Override
    public void run() {
        super.run();
        System.err.println("I'm in the ThreadAcceptConnection");
        hook_server.execute();
        System.err.println("I don't receive connections anymore");
    }
}
