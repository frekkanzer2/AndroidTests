package imt.exercise.xmastombola.multiplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import imt.exercise.xmastombola.R;
import imt.exercise.xmastombola.multiplayer.threads.ThreadAcceptConnections;

public class BoardActivity extends AppCompatActivity {

    private int MAXPLAYERS = 10;
    private int SERVERPORT = 9000; //9000 is the default port
    private volatile static int noPlayers = 1; //1 is the default player (board)
    private volatile static boolean anyoneWins = false;
    private BoardServer myServer = null;
    private TextView txt_status = null;
    private volatile static TextView txt_noPlayers = null;
    private TextView txt_nameBoard = null;
    private Button btn_startGame = null;
    private ArrayList<Button> list_allBtnNumbers = null;
    private static String STR_EXTNUM = "Extracted number: ";
    private static String STR_STASEC = "Game starting in 5 seconds...";
    private static String STR_STADON = "Playing game";
    private static String STR_FIRSTEXT = "First extraction in 3 seconds!";
    private boolean gameIsStarted = false;
    private Toast msgStartedGame = null;
    private Toast msgBoardWin = null;
    private Toast msgNeedPlayer = null;
    private ArrayList<Integer> arrayOfNumbers = null;
    private int extractedNumbers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Intent myIntent = getIntent();
        this.MAXPLAYERS = myIntent.getIntExtra("MAXPLAYERS", 0);
        this.SERVERPORT = myIntent.getIntExtra("SERVERPORT", 9000);

        initialize();
        txt_nameBoard.setText("Board of " + myIntent.getStringExtra("PLAYERNAME"));

        ThreadAcceptConnections tempThread = new ThreadAcceptConnections(myServer);
        tempThread.start();
        list_allBtnNumbers = getButtons();
        initializeNumbers();

        //Implementing start button pressure
        btn_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameIsStarted && BoardActivity.getNoPlayers() > 1){
                    msgStartedGame.show();
                    btn_startGame.setText("Close game");
                    //server closes connections
                    myServer.turnOffAccept();
                    txt_status.setText(STR_STASEC);
                    //waiting 5 seconds...
                    try{
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                        System.err.println("Problem waiting 5 seconds");
                        System.err.println("Closing app...");
                        System.exit(0);
                    }
                    //after waiting 5 seconds
                    txt_status.setText(STR_STADON);
                    txt_noPlayers.setText(STR_FIRSTEXT);
                    //waiting 3 seconds
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                        System.err.println("Problem waiting 3 seconds");
                        System.err.println("Closing app...");
                        System.exit(0);
                    }
                    initializePossibleNumbers();
                    //starting extraction
                    gameIsStarted = true;
                    Random generator = new Random();
                    while(!anyoneWins){
                        //extract new number
                        int myNumber = getRandomNumber(generator);
                        txt_noPlayers.setText(STR_EXTNUM + myNumber);
                        //sending number
                        myServer.sendNumber(myNumber);
                        //register on board
                        registerOnBoard(myNumber);
                        if (checkIfTombola()) {
                            myServer.closeServer();
                            msgBoardWin.show();
                            anyoneWins = true;
                            break;
                        }
                        //wait 3 seconds
                        try{
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                            System.err.println("Problem waiting 3 seconds");
                            System.err.println("Closing app...");
                            System.exit(0);
                        }
                        //restart
                    }
                } else if (gameIsStarted){
                    //stop game
                    myServer.closeServer();
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                        System.err.println("Problem waiting 1 second");
                        System.err.println("Closing app...");
                        System.exit(0);
                    }
                    System.exit(0);
                } else if (!gameIsStarted && BoardActivity.getNoPlayers() == 1){
                    msgNeedPlayer.show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myServer.closeServer();
    }

    private void initialize(){
        myServer = new BoardServer(SERVERPORT, MAXPLAYERS);
        txt_status = findViewById(R.id.txt_status);
        txt_noPlayers = findViewById(R.id.txt_numberOfPlayers);
        txt_nameBoard = findViewById(R.id.nameCard);
        btn_startGame = findViewById(R.id.btn_startGame);
        msgStartedGame = Toast.makeText(getApplicationContext(), "Game started! Good luck everyone!", Toast.LENGTH_SHORT);
        msgBoardWin = Toast.makeText(getApplicationContext(), "TOMBOLA!", Toast.LENGTH_SHORT);
        msgNeedPlayer = Toast.makeText(getApplicationContext(), "You need another player to start the game!", Toast.LENGTH_SHORT);
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
            list_allBtnNumbers.get(i-1).setBackgroundColor(Color.parseColor("#146B3A"));
            list_allBtnNumbers.get(i-1).setTag("false");
        }
    }

    public static synchronized void setNoPlayers(int newNumber){
        BoardActivity.noPlayers = newNumber;
    }

    public static synchronized int getNoPlayers(){
        return BoardActivity.noPlayers;
    }

    public static synchronized void refreshNoPlayers(){
        BoardActivity.txt_noPlayers.setText("Current players: " + BoardActivity.getNoPlayers());
    }

    private void registerOnBoard(int number){
        for (Button btn: list_allBtnNumbers)
            if (Integer.parseInt(btn.getText().toString()) == number && btn.getTag().toString().equals("false")){
                btn.setTag("true");
                btn.setBackgroundColor(Color.parseColor("#BB2528"));
            }
    }

    private boolean checkIfTombola(){
        boolean doneTombola = true;
        //First card
        for (int i = 0; i < 15; i++) {
            if (list_allBtnNumbers.get(i).getTag().toString().equals("false")){
                doneTombola = false;
                break;
            }
        }
        if (doneTombola) return true;
        //Second card
        doneTombola = true;
        for (int i = 15; i < 30; i++) {
            if (list_allBtnNumbers.get(i).getTag().toString().equals("false")){
                doneTombola = false;
                break;
            }
        }
        if (doneTombola) return true;
        //Third card
        doneTombola = true;
        for (int i = 30; i < 45; i++) {
            if (list_allBtnNumbers.get(i).getTag().toString().equals("false")){
                doneTombola = false;
                break;
            }
        }
        if (doneTombola) return true;
        //Fourth card
        doneTombola = true;
        for (int i = 45; i < 60; i++) {
            if (list_allBtnNumbers.get(i).getTag().toString().equals("false")){
                doneTombola = false;
                break;
            }
        }
        if (doneTombola) return true;
        //Fifth card
        doneTombola = true;
        for (int i = 60; i < 75; i++) {
            if (list_allBtnNumbers.get(i).getTag().toString().equals("false")){
                doneTombola = false;
                break;
            }
        }
        if (doneTombola) return true;
        //Sixth card
        doneTombola = true;
        for (int i = 75; i < 90; i++) {
            if (list_allBtnNumbers.get(i).getTag().toString().equals("false")){
                doneTombola = false;
                break;
            }
        }
        if (doneTombola) return true;
        return false;
    }

    private void initializePossibleNumbers(){
        arrayOfNumbers = new ArrayList<Integer>();
        extractedNumbers = 0;
        for (int i = 1; i < 91; i++)
            arrayOfNumbers.add(i);
    }

    private int getRandomNumber(Random generator){
        int index = generator.nextInt(90- extractedNumbers);
        int numberToReturn = arrayOfNumbers.get(index);
        arrayOfNumbers.remove(index);
        return numberToReturn;
    }

}
