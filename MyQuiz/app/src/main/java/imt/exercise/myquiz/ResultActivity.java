package imt.exercise.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity  extends AppCompatActivity {

    public int right = 0;
    public int wrong = 0;
    public TextView textFinalRight = null;
    public TextView textFinalWrong = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent previousIntent = getIntent();
        right = previousIntent.getIntExtra("CorrectAnswers", 1000);
        wrong = previousIntent.getIntExtra("WrongAnswers", 1000);
        textFinalRight = findViewById(R.id.text_finalCorrectResult);
        textFinalWrong = findViewById(R.id.text_finalWrongResult);
        textFinalRight.setText("Correct answers: " + right);
        textFinalWrong.setText("Wrong answers: " + wrong);
    }
}
