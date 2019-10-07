package imt.exercise.easygooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText input = null;
    public CheckBox cb_highway = null;
    public CheckBox cb_toll = null;
    public CheckBox cb_ferry = null;
    public Button executeButton = null;
    public String destination = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        associate();

        //Listener for the button
        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination = input.getText().toString();
                if (!destination.equals("")){
                    Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia&avoid=tf");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            }
        });

    }

    public void associate(){
        input = findViewById(R.id.editText);
        cb_highway = findViewById(R.id.checkBox_highway);
        cb_toll = findViewById(R.id.checkBox_toll);
        cb_ferry = findViewById(R.id.checkBox_ferry);
        executeButton = findViewById(R.id.buttonDrive);
    }

}
