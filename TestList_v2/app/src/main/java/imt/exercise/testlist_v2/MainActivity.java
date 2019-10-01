package imt.exercise.testlist_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import imt.exercise.customAdapters.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* THERE ARE THE ARRAYS FOR THE LIST */

        final String[] names = {
                "Alessandro Agnello", "Daniele Sorrentino", "Davide Alfieri",
                "Lorenzo Nunziante", "Mattia Spagnuolo"
        };

        String[] phones = {
                "3928769139", "3408205245", "3401713930", "3458342778", "3462174578"
        };

        Integer[] images = {
                R.drawable.img_contact, R.drawable.img_contact, R.drawable.img_contact,
                R.drawable.img_contact, R.drawable.img_contact
        };

        CustomAdapter myAdapter = new CustomAdapter(this, images, names, phones);
        ListView myView = findViewById(R.id.myPersonalizedList);
        myView.setAdapter(myAdapter);

        myView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "You've pressed " + names[position], Toast.LENGTH_LONG).show();
            }
        });

    }
}
