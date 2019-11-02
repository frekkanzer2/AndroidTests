package imt.exercise.xmastombola.multiplayer.threads;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import imt.exercise.xmastombola.multiplayer.CardMp;
import imt.exercise.xmastombola.multiplayer.MpCardsActivity;

public class ThreadWaitingNumbers extends Thread {

    private Socket connection = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private ArrayList<CardMp> listOfCards = null;
    private boolean isPlaying = true;

    public ThreadWaitingNumbers(Socket c, ObjectOutputStream o, ObjectInputStream i, ArrayList<CardMp> cards){
        this.connection = c;
        this.out = o;
        this.in = i;
        this.listOfCards = cards;
    }

    public void stopReceiving(){
        this.isPlaying = false;
    }

    public void run(){
        super.run();
        if (connection != null)
            while(isPlaying){
                int number = 0;
                try{
                    number = (int) in.readObject();
                    MpCardsActivity.setExtractedNumber(number);
                    MpCardsActivity.extractedNumber_update();
                    MpCardsActivity.showExtractedNumber();
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }catch(IOException e1){
                    e1.printStackTrace();
                }
                for (CardMp myCard: listOfCards){
                    ArrayList<View> listOfButtons = myCard.getArrayOfButtons();
                    for(View myView: listOfButtons){
                        Button myBtn = (Button) myView;
                        if (number == Integer.parseInt(myBtn.getText().toString()) && myBtn.getTag().toString().equals("false")){
                            myBtn.setTag("true");
                            myBtn.setBackgroundColor(Color.parseColor("#BB2528"));
                        }
                    }
                }
            }
    }

    public boolean getStatus(){
        return isPlaying;
    }

}
