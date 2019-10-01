package imt.exercise.testlist_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import imt.exercise.customAdapters.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] names = {
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

    }
}
