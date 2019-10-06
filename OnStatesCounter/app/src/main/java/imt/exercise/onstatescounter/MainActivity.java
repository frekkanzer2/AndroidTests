package imt.exercise.onstatescounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public int statesCounter = 0;
    public ArrayList<String> messagesList = new ArrayList<String>();
    public ListView layout_list = null;
    public TextView output = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle", "onCreate invoked");

        if (savedInstanceState != null){
            Log.d("Lifecycle", "Taking params");
            statesCounter = savedInstanceState.getInt("counter");
            messagesList = savedInstanceState.getStringArrayList("msgsList");
        }

        layout_list = findViewById(R.id.controlList);
        output = findViewById(R.id.counterText);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this, R.layout.item_list, messagesList
        );
        layout_list.setAdapter(listAdapter);

        statesCounter++;
        messagesList.add(statesCounter + ": onCreate executed");
        update();
    }

    protected void onStart(){
        statesCounter++;
        messagesList.add(statesCounter + ": onStart executed");
        update();
        super.onStart();
    }

    protected void onResume(){
        statesCounter++;
        messagesList.add(statesCounter + ": onResume executed");
        update();
        super.onResume();
    }

    protected void onPause(){
        statesCounter++;
        messagesList.add(statesCounter + ": onPause executed");
        update();
        super.onPause();
    }

    protected void onStop(){
        statesCounter++;
        messagesList.add(statesCounter + ": onStop executed");
        update();
        super.onStop();
    }

    protected void onDestroy(){
        messagesList.add(statesCounter + ": onDestroy executed");
        update();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        statesCounter++;
        savedInstanceState.putInt("counter", statesCounter);
        savedInstanceState.putStringArrayList("msgsList", messagesList);
        Log.d("Alert", "Values saved");
        super.onSaveInstanceState(savedInstanceState);
    }

    public void update(){
        output.setText("Counter: " + statesCounter);
    }

    public void reset(View v){
        statesCounter = 0;
        while(messagesList.size() > 0){
            messagesList.remove(0);
        }
        output.setText("Counter: " + statesCounter);
    }

}
