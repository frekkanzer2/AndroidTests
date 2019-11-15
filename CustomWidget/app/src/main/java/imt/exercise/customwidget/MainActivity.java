package imt.exercise.customwidget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomWidget myWidget = new CustomWidget(getApplicationContext(), 100, 100);
        myWidget.setColor(Color.RED);
        LinearLayout myLayout = findViewById(R.id.myContainer);
        myLayout.addView(myWidget);

    }

}
