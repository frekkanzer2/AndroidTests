package imt.exercise.fastclick;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static final Integer CODE_ACTIVITY = 100;
    private TextView txtCounter;
    private TableLayout container;
    private ArrayList<Button> numButtons = new ArrayList<>();
    private Button btnStart;
    //The following ArrayList contains all numbers
    private ArrayList<Integer> allNumbers = new ArrayList<>();
    //The following ArrayList contains extractable numbers for TextView
    private ArrayList<Integer> numbersToDisplay = new ArrayList<>();
    private Instant startTime;
    private Instant endTime;
    private Integer scoreCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        this.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreCounter = 0;
                btnStart.setVisibility(View.INVISIBLE);
                for (int i = 1; i < 10; i++)
                    numbersToDisplay.add(i);
                Collections.shuffle(numbersToDisplay);
                for (Button b: numButtons){
                    b.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Button clicked = (Button) v;
                            int pressedNumber = Integer.parseInt(clicked.getText().toString());
                            int displayedNumber = Integer.parseInt(txtCounter.getText().toString());
                            if (pressedNumber == displayedNumber) {
                                System.err.println("Right number clicked!");
                                scoreCounter++;
                                System.err.println("New score: " + scoreCounter);
                            } else {
                                //Lowering time here
                                System.err.println("Wrong number clicked...");
                            }
                            newTurn();
                        }
                    });
                }
                startTime = Instant.now();
                newTurn();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.CODE_ACTIVITY && resultCode == Activity.RESULT_OK) {
            reset();
        }
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
        if (numbersToDisplay.size() > 0){
            txtCounter.setText("" + numbersToDisplay.get(0));
            numbersToDisplay.remove(0);
            Collections.shuffle(numbersToDisplay);
            randomizeButtons();
        } else {
            System.err.println("Game ended. Going to the results screen.");
            //Go to the results screen here
            endTime = Instant.now();
            gotoResults();
        }

    }

    private void gotoResults(){
        Integer wrongReplies = 9 - scoreCounter;
        Long elapsedTime = (Long) (Duration.between(startTime, endTime).toMillis() / 1000) % 60;
        if (wrongReplies > 0)
            elapsedTime += (wrongReplies * 5);
        Intent resultsActivity = new Intent(this, ResultsActivity.class);
        resultsActivity.putExtra("SCORE_TIME", elapsedTime);
        startActivityForResult(resultsActivity, MainActivity.CODE_ACTIVITY);
    }

    private void reset(){
        btnStart.setVisibility(View.VISIBLE);
        for (Button b: numButtons) {
            b.setText("0");
            b.setOnClickListener(null);
        }
        txtCounter.setText("0");
    }

}
