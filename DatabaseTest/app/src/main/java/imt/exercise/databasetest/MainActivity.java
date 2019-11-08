package imt.exercise.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txt_playerName = null;
    private TextView txt_actualScore = null;
    private TextView txt_displayedHighscore = null;
    private Button btn_plus = null;
    private Button btn_minus = null;
    private Button btn_displayHighscore = null;
    private Button btn_saveScore = null;
    private int myCounter = 0;
    private String playerName = null;
    private SharedPreferences preference = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        //LISTENER FOR ADD BUTTON
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCounter++;
                txt_actualScore.setText("Actual points: " + myCounter);
            }
        });

        //LISTENER FOR SUB BUTTON
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCounter--;
                txt_actualScore.setText("Actual points: " + myCounter);
            }
        });

        //LISTENER FOR SAVE SCORE BUTTON
        btn_saveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor savesEditor = preference.edit();
                playerName = txt_playerName.getText().toString();
                savesEditor.putString("PLAYERNAME", playerName);
                savesEditor.putInt("SCORE", myCounter);
                savesEditor.commit();
            }
        });

        //LISTENER FOR TEXT CHANGING
        txt_playerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txt_playerName.setText("");
                txt_actualScore.setText("0");
                myCounter = 0;
                playerName = null;
            }
        });

    }

    private void initialize(){
        txt_playerName = findViewById(R.id.txt_playerName);
        txt_actualScore = findViewById(R.id.txt_counterPoints);
        txt_displayedHighscore = findViewById(R.id.txt_highscore);
        btn_plus = findViewById(R.id.btn_add);
        btn_minus = findViewById(R.id.btn_sub);
        btn_displayHighscore = findViewById(R.id.btn_displayHighscore);
        btn_saveScore = findViewById(R.id.btn_saveScore);
        preference = getSharedPreferences("pref", MODE_PRIVATE);
    }
}
