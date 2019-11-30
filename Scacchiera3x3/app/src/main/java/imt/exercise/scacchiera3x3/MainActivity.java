package imt.exercise.scacchiera3x3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_counter = null;
    private TextView[] tv_arrayOfNumbers = null;
    private Button[] btn_controlButtons = null;
    private Button btn_reset = null;
    private Button btn_cheat = null;
    private int[] allNumbers = null;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        shuffle();
        updateNumbers();

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffle();
                updateNumbers();
                tv_counter.setText("0");
                counter = 0;
            }
        });

        btn_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 9; i++)
                    allNumbers[i] = i+1;
                updateNumbers();
            }
        });

    }

    private void initialize(){
        tv_arrayOfNumbers = new TextView[9];
        btn_controlButtons = new Button[12];
        tv_counter = findViewById(R.id.txt_counter);
        tv_arrayOfNumbers[0] = findViewById(R.id.txt_numb_1);
        tv_arrayOfNumbers[1] = findViewById(R.id.txt_numb_2);
        tv_arrayOfNumbers[2] = findViewById(R.id.txt_numb_3);
        tv_arrayOfNumbers[3] = findViewById(R.id.txt_numb_4);
        tv_arrayOfNumbers[4] = findViewById(R.id.txt_numb_5);
        tv_arrayOfNumbers[5] = findViewById(R.id.txt_numb_6);
        tv_arrayOfNumbers[6] = findViewById(R.id.txt_numb_7);
        tv_arrayOfNumbers[7] = findViewById(R.id.txt_numb_8);
        tv_arrayOfNumbers[8] = findViewById(R.id.txt_numb_9);
        btn_controlButtons[0] = findViewById(R.id.btn_control_1);
        btn_controlButtons[1] = findViewById(R.id.btn_control_2);
        btn_controlButtons[2] = findViewById(R.id.btn_control_3);
        btn_controlButtons[3] = findViewById(R.id.btn_control_4);
        btn_controlButtons[4] = findViewById(R.id.btn_control_5);
        btn_controlButtons[5] = findViewById(R.id.btn_control_6);
        btn_controlButtons[6] = findViewById(R.id.btn_control_7);
        btn_controlButtons[7] = findViewById(R.id.btn_control_8);
        btn_controlButtons[8] = findViewById(R.id.btn_control_9);
        btn_controlButtons[9] = findViewById(R.id.btn_control_10);
        btn_controlButtons[10] = findViewById(R.id.btn_control_11);
        btn_controlButtons[11] = findViewById(R.id.btn_control_12);
        btn_reset = findViewById(R.id.btn_reset);
        btn_cheat = findViewById(R.id.btn_cheat);
        allNumbers = new int[9];
        for (int i = 0; i < 9; i++)
            allNumbers[i] = i+1;
        for (int i = 0; i < 9; i++)
            tv_arrayOfNumbers[i].setGravity(Gravity.CENTER);
        updateNumbers();
    }

    public void updateNumbers(){
        for (int i = 0; i < 9; i++)
            tv_arrayOfNumbers[i].setText(""+allNumbers[i]);
    }

    public void go_up(int column){
        column--;
        int savedNumber = allNumbers[0+column];
        allNumbers[0+column] = allNumbers[3+column];
        allNumbers[3+column] = allNumbers[6+column];
        allNumbers[6+column] = savedNumber;
    }
    public void go_down(int column){
        column--;
        int savedNumber = allNumbers[6+column];
        allNumbers[6+column] = allNumbers[3+column];
        allNumbers[3+column] = allNumbers[0+column];
        allNumbers[0+column] = savedNumber;
    }
    public void go_left(int row){
        row--;
        int savedNumber = allNumbers[0+(row*3)];
        allNumbers[0+(row*3)] = allNumbers[1+(row*3)];
        allNumbers[1+(row*3)] = allNumbers[2+(row*3)];
        allNumbers[2+(row*3)] = savedNumber;
    }
    public void go_right(int row){
        row--;
        int savedNumber = allNumbers[2+(row*3)];
        allNumbers[2+(row*3)] = allNumbers[1+(row*3)];
        allNumbers[1+(row*3)] = allNumbers[0+(row*3)];
        allNumbers[0+(row*3)] = savedNumber;
    }

    public void buttonClicked(View v){
        if (canPlay()) {
            Button btnClicked = (Button) v;
            switch (btnClicked.getId()) {
                case R.id.btn_control_1:
                    go_up(1);
                    break;
                case R.id.btn_control_2:
                    go_up(2);
                    break;
                case R.id.btn_control_3:
                    go_up(3);
                    break;
                case R.id.btn_control_4:
                    go_left(1);
                    break;
                case R.id.btn_control_5:
                    go_right(1);
                    break;
                case R.id.btn_control_6:
                    go_left(2);
                    break;
                case R.id.btn_control_7:
                    go_right(2);
                    break;
                case R.id.btn_control_8:
                    go_left(3);
                    break;
                case R.id.btn_control_9:
                    go_right(3);
                    break;
                case R.id.btn_control_10:
                    go_down(1);
                    break;
                case R.id.btn_control_11:
                    go_down(2);
                    break;
                case R.id.btn_control_12:
                    go_down(3);
                    break;
            }
            updateNumbers();
            counter++;
            tv_counter.setText("" + counter);
        } else {
            Toast.makeText(getApplicationContext(), "YOU WON WITH " + tv_counter.getText().toString() + " ATTEMPTS" , Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkWinner(){
        for (int i = 0; i < 9; i++){
            if (Integer.parseInt(tv_arrayOfNumbers[i].getText().toString()) != i+1)
                return false;
        }
        return true;
    }

    public boolean canPlay(){
        boolean myBool = checkWinner();
        return !myBool;
    }

    public void shuffle(){
        List<Integer> myArray = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++)
            myArray.add(allNumbers[i]);
        Collections.shuffle(myArray);
        for (int i = 0; i < 9; i++)
            allNumbers[i] = myArray.get(i);
        Toast.makeText(getApplicationContext(), "Good luck!", Toast.LENGTH_LONG).show();
    }

}
