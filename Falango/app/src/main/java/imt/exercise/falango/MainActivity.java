package imt.exercise.falango;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnPizzeria = null;
    private Button btnPub = null;
    private Button btnBar = null;
    private Button btnSushi = null;
    private TextView chosenName = null;
    private ArrayList<String> listPizzeria = null;
    private ArrayList<String> listPub = null;
    private ArrayList<String> listBar = null;
    private ArrayList<String> listSushi = null;
    private Configurator container = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPizzeria = findViewById(R.id.btnPizzeria);
        btnPub = findViewById(R.id.btnPub);
        btnBar = findViewById(R.id.btnBar);
        btnSushi = findViewById(R.id.btnSushi);
        chosenName = findViewById(R.id.textResult);
        //creation of arraylists
        container = new Configurator();
        listPizzeria = container.getPizzeriaList();
        listPub = container.getPubList();
        listBar = container.getBarList();
        listSushi = container.getSushiList();
        //listener association
        btnPizzeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setRandomName(listPizzeria);
                chosenName.setText(container.getTakedName());
            }
        });
        btnPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setRandomName(listPub);
                chosenName.setText(container.getTakedName());
            }
        });
        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setRandomName(listBar);
                chosenName.setText(container.getTakedName());
            }
        });
        btnSushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.setRandomName(listSushi);
                chosenName.setText(container.getTakedName());
            }
        });
        //Set the TextView clickable
        chosenName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!chosenName.getText().toString().equals("")){
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + chosenName.getText().toString());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
                return false;
            }
        });
    }
}
