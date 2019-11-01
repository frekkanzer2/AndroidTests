package imt.exercise.xmastombola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardsActivity extends AppCompatActivity {

    private int numberOfCards = 0;
    private String playerName = null;
    private EditText txt_newNumber = null;
    private Button btn_add = null;
    private Card[] collection = null;
    private Toast msgConfirm = null;
    private Toast msgError = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        msgConfirm = Toast.makeText(getApplicationContext(), "Number added", Toast.LENGTH_SHORT);
        msgError = Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_SHORT);

        Intent myIntent = getIntent();
        numberOfCards = myIntent.getIntExtra("NOCARDS", 1);
        playerName = myIntent.getStringExtra("PLAYERNAME");

        txt_newNumber = findViewById(R.id.txt_newNumber);
        btn_add = findViewById(R.id.btn_add);

        collection = new Card[numberOfCards];
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        for (int i = 0; i < numberOfCards; i++){
            collection[i] = new Card(playerName, i+1);
            transaction.add(R.id.layout_toAppend, collection[i], null);
        }
        transaction.commit();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(txt_newNumber.getText().toString());
                if (number > 0 && number <= 90)
                    msgConfirm.show();
                else msgError.show();
                for (int i = 0; i < numberOfCards; i++){
                    Card myCard = collection[i];
                    ArrayList<View> listOfButtons = myCard.getArrayOfButtons();
                    for (View btnView: listOfButtons){
                        Button myBtn = (Button) btnView;
                        int btnNumber = Integer.parseInt(myBtn.getText().toString());
                        if (number == btnNumber){
                            myBtn.setTag("true");
                            myBtn.setBackgroundColor(Color.parseColor("#BB2528"));
                        }
                    }
                }
            }
        });

    }
}
