package imt.exercise.clientservertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    public static volatile TextView statusServer = null;
    public static volatile TextView statusClient = null;
    public ServerThread st = null;

    public static synchronized void changeStatusServer(String msg){
        MainActivity.statusServer.setText(msg);
    }

    public static synchronized void changeStatusClient(String msg){
        MainActivity.statusClient.setText(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusServer = findViewById(R.id.statusServer);
        statusClient = findViewById(R.id.statusClient);

    }

    public void createServer(View v){
        st = new ServerThread();
        st.start();
    }

    public void createClient(View v){
        ClientThread ct = new ClientThread();
        ct.start();
    }

    public void closeServer(View v){
        st.closeServer();
    }

}
