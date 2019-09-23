package com.example.changetextcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textToChange = null;
    private Button buttonChanger = null;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textToChange = findViewById(R.id.textToChange);
        buttonChanger = findViewById(R.id.buttonChanger);
    }

    public void executeChange(View v){
        if (flag == false) {
            textToChange.setBackgroundColor(Color.rgb(235, 128, 52));
            textToChange.setTextColor(Color.rgb(52, 76, 235));
            flag = true;
        } else {
            textToChange.setBackgroundColor(Color.rgb(52, 76, 235));
            textToChange.setTextColor(Color.rgb(235, 128, 52));
            flag = false;
        }
    }
}
