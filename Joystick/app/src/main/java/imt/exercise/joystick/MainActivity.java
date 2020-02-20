package imt.exercise.joystick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private MovementFragment internalScreen;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        internalScreen = (MovementFragment) fm.findFragmentById(R.id.myFragment);

        input = findViewById(R.id.txt_mov);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (input.getText().toString().compareTo("") != 0)
                    if (Integer.parseInt(input.getText().toString()) > 300){
                        input.setText("300");
                    }
            }
        });

    }

    public void clicked(View v){
        Button bClicked = (Button) v;
        String name = bClicked.getText().toString();
        BallView tempBall = internalScreen.getObjBall();
        switch(name){
            case "CENTER":
                tempBall.traslate(internalScreen.getContainer().getWidth()/2,
                        internalScreen.getContainer().getHeight()/2);
                tempBall.invalidate();
                break;
            case "LEFT":
                tempBall.moveX(Integer.parseInt(input.getText().toString()), false);
                tempBall.invalidate();
                break;
            case "RIGHT":
                tempBall.moveX(Integer.parseInt(input.getText().toString()), true);
                tempBall.invalidate();
                break;
            case "UP":
                tempBall.moveY(Integer.parseInt(input.getText().toString()), false);
                tempBall.invalidate();
                break;
            case "DOWN":
                tempBall.moveY(Integer.parseInt(input.getText().toString()), true);
                tempBall.invalidate();
                break;
        }
    }
}
