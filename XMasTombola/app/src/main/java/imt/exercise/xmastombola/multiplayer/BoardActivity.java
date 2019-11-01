package imt.exercise.xmastombola.multiplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import imt.exercise.xmastombola.R;

public class BoardActivity extends AppCompatActivity {

    private int MAXPLAYERS = 3;
    private int SERVERPORT = 9000; //9000 is the default port
    private int noPlayers = 1; //1 is the default player (board)
    private BoardServer myServer = null;
    private TextView txt_status = null;
    private TextView txt_noPlayers = null;
    private TextView txt_nameBoard = null;
    private Button btn_startGame = null;
    private ArrayList<Button> list_allBtnNumbers = null;
    private static String STR_EXTNUM = "Extracted number: ";
    private static String STR_STASEC = "Game starting in 5 seconds...";
    private static String STR_STADON = "Playing game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Intent myIntent = getIntent();
        this.MAXPLAYERS = myIntent.getIntExtra("MAXPLAYERS", 0);
        this.SERVERPORT = myIntent.getIntExtra("SERVERPORT", 9000);

        initialize();
        myServer.execute();
        list_allBtnNumbers = getButtons();
        initializeNumbers();


    }

    private void initialize(){
        myServer = new BoardServer(SERVERPORT, MAXPLAYERS);
        txt_status = findViewById(R.id.txt_status);
        txt_noPlayers = findViewById(R.id.txt_numberOfPlayers);
        txt_nameBoard = findViewById(R.id.nameCard);
        btn_startGame = findViewById(R.id.btn_startGame);
    }

    protected ArrayList<Button> getButtons(){
        ArrayList<View> listOfTouchables = findViewById(R.id.root_card).getTouchables();
        ArrayList<Button> listOfButtonsToReturn = new ArrayList<Button>();
        for (View v: listOfTouchables){
            Button btn = (Button) v;
            listOfButtonsToReturn.add(btn);
        }
        return listOfButtonsToReturn;
    }

    private void initializeNumbers(){
        for (int i = 1; i < 91; i++){
            list_allBtnNumbers.get(i-1).setText(Integer.toString(i));
        }
    }

}
