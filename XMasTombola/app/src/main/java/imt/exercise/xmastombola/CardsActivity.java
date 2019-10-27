package imt.exercise.xmastombola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class CardsActivity extends AppCompatActivity {

    private int numberOfCards = 0;
    private String playerName = null;
    private EditText txt_newNumber = null;
    private Button btn_add = null;
    private Card[] collection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

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

    }
}
