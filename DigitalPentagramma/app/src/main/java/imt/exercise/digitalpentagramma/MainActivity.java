package imt.exercise.digitalpentagramma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout myLayout = findViewById(R.id.myContainer);
        Pentagramma myPent = new Pentagramma(getApplicationContext(), 700, 50, 50);
        myPent.addNote(new Note(100, 100));
        myPent.addNote(new Note(200, 120));
        myPent.addNote(new Note(300, 140));
        myLayout.addView(myPent);
        myPent.addNote(new Note(400, 160));
        myPent.invalidate();

    }
}
