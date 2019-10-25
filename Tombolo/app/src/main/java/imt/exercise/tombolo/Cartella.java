package imt.exercise.tombolo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cartella extends Fragment implements View.OnClickListener {

    int[] numbers = null;

    public Cartella(){

    }

    public void setNumbers(int[] nums){
        this.numbers = nums;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_cartella, group, false);
        ArrayList<View> arrayButtons = v.findViewById(R.id.table).getTouchables();
        int i = 0;
        for (View item: arrayButtons){
            Button tempButton = (Button) item;
            if (tempButton != null && numbers != null){
                System.err.println("PORCODDIO SONO ENTRATO");
                tempButton.setOnClickListener(this);
                tempButton.setText("" + numbers[i]);
                tempButton.setTextColor(Color.BLACK);
                i++;
                tempButton.setTag("false");
            }
        }
        return v;
    }

    public void onClick(View v){
        Button myBtn = (Button) v;
        Object btnTag = myBtn.getTag();
        if (btnTag.toString().equals("false")){
            myBtn.setTag("true");
            myBtn.setTextColor(Color.RED);
        }
    }

}
