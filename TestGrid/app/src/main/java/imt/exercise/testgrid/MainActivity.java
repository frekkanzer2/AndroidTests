package imt.exercise.testgrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView counterText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterText = findViewById(R.id.textCounter);

        final String[] myArray = {
                "first", "second", "third", "fourth", "fifth", "sixth", "seventh",
                "eighth", "ninth", "tenth", "eleventh", "twelfth", "thirteenth", "fourteenth"
        };
        GridView myGrid = findViewById(R.id.gridToAppend);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
                this, R.layout.item, myArray
        );
        myGrid.setAdapter(myAdapter);

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pressedElement = myArray[position];
                int strSize = pressedElement.length();
                int tempCounter = Integer.parseInt(counterText.getText().toString());
                tempCounter += strSize;
                counterText.setText(Integer.toString(tempCounter));
            }
        });

    }
}
