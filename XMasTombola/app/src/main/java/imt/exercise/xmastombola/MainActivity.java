package imt.exercise.xmastombola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnConfirm = null;
    private EditText txtInputName = null;
    private Spinner spnCards = null;
    private TextView txtPrice = null;
    //Online section
    private Button btnHost = null; //play with board
    private Button btnClient = null; //play with cards

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConfirm = findViewById(R.id.btn_confirm);
        txtInputName = findViewById(R.id.input_name);
        spnCards = findViewById(R.id.mySpinner);
        txtPrice = findViewById(R.id.txt_price);

        btnHost = findViewById(R.id.btn_online_board);
        btnClient = findViewById(R.id.btn_online_cards);

        String item = spnCards.getSelectedItem().toString();
        int numberOfCards = Integer.parseInt(item);
        if (numberOfCards == 1) txtPrice.setText("Price: 10 cents");

        spnCards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        txtPrice.setText("Price 10 cents");
                        break;
                    case 1:
                        txtPrice.setText("Price 20 cents");
                        break;
                    case 2:
                        txtPrice.setText("Price 30 cents");
                        break;
                    case 3:
                        txtPrice.setText("Price 40 cents");
                        break;
                    case 4:
                        txtPrice.setText("Price 50 cents");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = txtInputName.getText().toString();
                if (!playerName.equals("Insert here your name")){
                    int nocards = Integer.parseInt(spnCards.getSelectedItem().toString());
                    Intent newActivity = new Intent(MainActivity.this, CardsActivity.class);
                    newActivity.putExtra("PLAYERNAME", playerName);
                    newActivity.putExtra("NOCARDS", nocards);
                    startActivity(newActivity);
                }
            }
        });

    }
}
