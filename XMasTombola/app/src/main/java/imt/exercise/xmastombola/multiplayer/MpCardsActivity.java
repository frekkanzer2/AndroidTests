package imt.exercise.xmastombola.multiplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import imt.exercise.xmastombola.R;
import imt.exercise.xmastombola.multiplayer.threads.ClientThread;

public class MpCardsActivity extends AppCompatActivity {

    private static Toast msgNumberTaken = null;
    private volatile static int numberTaken = 0;
    private int numberOfCards = 1;
    private String playerName = null;
    private int serverPort = 9000;
    private CardMp[] collection = null;
    private ClientThread myClient = null;

    private volatile static TextView txt_serverStatus = null;
    private volatile static TextView txt_extractedNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_cards);

        txt_serverStatus = findViewById(R.id.txt_serverStatus);
        txt_extractedNumber = findViewById(R.id.txt_receivedNumber);

        msgNumberTaken = Toast.makeText(getApplicationContext(), "Extracted number: " + numberTaken, Toast.LENGTH_SHORT);

        Intent myIntent = getIntent();
        numberOfCards = myIntent.getIntExtra("NOCARDS", 1);
        playerName = myIntent.getStringExtra("PLAYERNAME");
        serverPort = myIntent.getIntExtra("SERVERPORT", 9000);

        collection = new CardMp[numberOfCards];
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        for (int i = 0; i < numberOfCards; i++){
            collection[i] = new CardMp(playerName, i+1);
            transaction.add(R.id.layout_toAppend, collection[i], null);
        }
        transaction.commit();

        //Client connection
        ArrayList<CardMp> listOfCards = new ArrayList<CardMp>();
        for (int i = 0; i < collection.length; i++)
            listOfCards.add(collection[i]);
        myClient = new ClientThread(serverPort, listOfCards);
        myClient.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myClient.closeConnection();
    }

    public static synchronized void showExtractedNumber(){
        MpCardsActivity.msgNumberTaken.show();
    }

    public static synchronized void setExtractedNumber(int number){
        MpCardsActivity.numberTaken = number;
    }

    /*METHODS FOR TEXT VIEWS*/
    public static synchronized void serverStatus_setText(String text){
        MpCardsActivity.txt_serverStatus.setText(text);
    }

    public static synchronized void extractedNumber_update(){
        MpCardsActivity.txt_extractedNumber.setText("Extracted number: " + MpCardsActivity.numberTaken);
    }

}
