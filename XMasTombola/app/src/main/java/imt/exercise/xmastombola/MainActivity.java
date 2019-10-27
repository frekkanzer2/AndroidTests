package imt.exercise.xmastombola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnConfirm = null;
    private EditText txtInputName = null;
    private Spinner spnCards = null;
    private TextView txtPrice = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConfirm = findViewById(R.id.btn_confirm);
        txtInputName = findViewById(R.id.input_name);
        spnCards = findViewById(R.id.mySpinner);
        txtPrice = findViewById(R.id.txt_price);

        String item = spnCards.getSelectedItem().toString();
        int numberOfCards = Integer.parseInt(item);
        if (numberOfCards == 1) txtPrice.setText("Price: 10 cents");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = txtInputName.getText().toString();
                if (!playerName.equals("Insert here your name")){

                }
            }
        });

    }
}
