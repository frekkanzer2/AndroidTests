package imt.exercise.fastclick;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private TextView actualScore;
    private TextView recordScore;
    private Button btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        actualScore = findViewById(R.id.txtScore);
        recordScore = findViewById(R.id.txtRecord);
        btnRestart = findViewById(R.id.btnRestart);

        SharedPreferences sp = getSharedPreferences("record", Context.MODE_PRIVATE);
        Long record = sp.getLong("SCORE_TIME", 0);
        recordScore.setText(record + "");

        Intent storage = getIntent();
        Long elapsedTime = storage.getLongExtra("SCORE_TIME", 0);
        if (elapsedTime == 0) {
            System.err.println("Error with time");
            actualScore.setText("ERROR");
        } else {
            actualScore.setText("" + elapsedTime);
            if (elapsedTime < Long.parseLong(recordScore.getText().toString())){
                recordScore.setText("" + elapsedTime);
                SharedPreferences.Editor editor = sp.edit();
                editor.putLong("SCORE_TIME", elapsedTime);
                editor.commit();
            }
        }

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(0);
    }
}
