package imt.exercise.testlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring the array of items here
        final String[] strItems = {
                "Abby", "Daduccio", "Lambojet", "Giannolo", "Alex", "Giggi"
        };
        //Getting the ListView
        ListView list = findViewById(R.id.listToEdit);
        //Creating the adapter
            //Constructor: context (this), view item (item_list), array of records
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(
                this, R.layout.item_list, strItems
        );
        //Connect adapter to ListView
        list.setAdapter(myAdapter);
        //Setting a listener to the ListView
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //When an item is clicked, a Toast text will appear on the screen
                Toast.makeText(
                        getApplicationContext(),
                        "You've selected " + strItems[position],
                        Toast.LENGTH_LONG
                        ).show();
            }
        });

    }
}
