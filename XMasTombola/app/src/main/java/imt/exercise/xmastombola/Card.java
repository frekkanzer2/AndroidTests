package imt.exercise.xmastombola;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class Card extends Fragment {

    private String playerName = null;
    private int cardNumber = 0;
    private int[] arrayOfNumbers = null;

    public Card(String playerName, int cardNo){
        this.playerName = playerName;
        this.cardNumber = cardNo;
        arrayOfNumbers = generateNumbers();
    }

    private static int getRandomInt(int low, int high) {
        Random r = new Random();
        high += 1;
        return r.nextInt(high-low) + low;
    }

    private int[] generateNumbers(){
        int[] arrayToReturn = new int[15];
        int arrayIndex = 0;
        int position = 0;
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        for (int i = 1; i < 91; i++) listOfNumbers.add(i);
        for (int i = 0; i < 3; i++) {
            //first
            position = getRandomInt(0, 17 - arrayIndex);
            System.err.println("Random gotten position: " + position);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            System.err.println("Size of the array: " + listOfNumbers.size());
            System.err.println("arrayIndex: " + arrayIndex);
            //second
            position = getRandomInt(18 - arrayIndex, 35 - arrayIndex);
            System.err.println("Random gotten position: " + position);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            System.err.println("Size of the array: " + listOfNumbers.size());
            System.err.println("arrayIndex: " + arrayIndex);
            //third
            position = getRandomInt(36 - arrayIndex, 53 - arrayIndex);
            System.err.println("Random gotten position: " + position);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            System.err.println("Size of the array: " + listOfNumbers.size());
            System.err.println("arrayIndex: " + arrayIndex);
            //fourth
            position = getRandomInt(54 - arrayIndex, 71 - arrayIndex);
            System.err.println("Random gotten position: " + position);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            System.err.println("Size of the array: " + listOfNumbers.size());
            System.err.println("arrayIndex: " + arrayIndex);
            //fifth
            position = getRandomInt(72 - arrayIndex, 89 - arrayIndex);
            System.err.println("Random gotten position: " + position);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            System.err.println("Size of the array: " + listOfNumbers.size());
            System.err.println("arrayIndex: " + arrayIndex);
        }
        return arrayToReturn;
    }

    public View onCreateView(LayoutInflater creator, ViewGroup group, Bundle container){
        System.err.println("onCreateView started");
        View v = creator.inflate(R.layout.cartella, null);
        System.err.println("Getted the view inflated");
        //arrayOfButtons will contain an array of Buttons (numbers on the card
        ArrayList<View> arrayOfButtons = v.findViewById(R.id.root_card).getTouchables();
        System.err.println("Buttons found: " + arrayOfButtons.size());
        int k = 0;
        System.err.println("Entering in condition...");
        if (arrayOfButtons != null)
            for (View btnView: arrayOfButtons){
                Button myBtn = (Button) btnView;
                System.err.println("Button taken: " + myBtn.getText());
                myBtn.setTag("false");
                System.err.println("Tag insered at false.");
                System.err.println("arrayOfNumbers size: " + arrayOfNumbers.length);
                myBtn.setText(Integer.toString(arrayOfNumbers[k]));
                myBtn.setBackgroundColor(Color.parseColor("#146B3A"));
                myBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button btn = (Button) v;
                        if (btn.getTag().toString().equals("false")){
                            btn.setTag("true");
                            btn.setBackgroundColor(Color.parseColor("#BB2528"));
                        } else {
                            btn.setTag("false");
                            btn.setBackgroundColor(Color.parseColor("#146B3A"));
                        }
                    }
                });
                k++;
            }

        TextView intestation = v.findViewById(R.id.nameCard);
        intestation.setText("Card of " + this.playerName + ", n. " + this.cardNumber);
        return v;
    }

}
