package imt.exercise.fastclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private TextView txtCounter;
    private TableLayout container;
    private ArrayList<Button> numButtons = new ArrayList<>();
    private Button btnStart;
    private ArrayList<Integer> allNumbers = new ArrayList<>();
    private ArrayList<Integer> numbersToDisplay = new ArrayList<>();
    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        this.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomizeButtons();
                btnStart.setVisibility(View.INVISIBLE);
                for (int i = 1; i < 10; i++)
                    numbersToDisplay.add(i);
                Collections.shuffle(numbersToDisplay);
                startTime = LocalTime.now();
            }
        });

    }

    private void initialize(){
        this.txtCounter = findViewById(R.id.txtCounter);
        this.container = findViewById(R.id.table);
        this.btnStart = findViewById(R.id.btnStart);
        //Getting all buttons
        for (int i = 0; i < this.container.getChildCount(); i++){
            View child = this.container.getChildAt(i);
            if (child instanceof TableRow) {
                TableRow row = (TableRow) child;
                ArrayList<View> btnsInRow = row.getTouchables();
                for (View v: btnsInRow){
                    if (v instanceof Button) {
                        Button tempBtn = (Button) v;
                        this.numButtons.add(tempBtn);
                    }
                }
            }
        }
        for (int i = 1; i < 10; i++)
            this.allNumbers.add(i);
    }

    private void randomizeButtons(){
        Collections.shuffle(this.allNumbers);
        for (int i = 0; i < this.allNumbers.size(); i++)
            this.numButtons.get(i).setText(this.allNumbers.get(i) + "");
    }

    private void newTurn(){

    }

}
