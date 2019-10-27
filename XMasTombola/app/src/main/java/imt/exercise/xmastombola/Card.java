package imt.exercise.xmastombola;

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
        for (int i = 0; i < 3; i++){
            //first
            position = getRandomInt(1, 18-arrayIndex);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            //second
            position = getRandomInt(19-arrayIndex, 36-arrayIndex);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            //third
            position = getRandomInt(37-arrayIndex, 54-arrayIndex);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            //fourth
            position = getRandomInt(55-arrayIndex, 72-arrayIndex);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
            //fifth
            position = getRandomInt(73-arrayIndex, 90-arrayIndex);
            arrayToReturn[arrayIndex] = listOfNumbers.get(position);
            listOfNumbers.remove(position);
            arrayIndex++;
        }
        System.err.println(arrayToReturn.toString());
        return arrayToReturn;
    }

}
