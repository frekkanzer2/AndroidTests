package imt.exercise.performancetest.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import imt.exercise.performancetest.R;

public class MainActivity extends AppCompatActivity {

    private Spinner spn_selection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spn_selection = findViewById(R.id.list_threads);
    }

    public void goto_OperationINI(View v){
        String spinnerSelection = spn_selection.getSelectedItem().toString();
        int thSelection = Integer.parseInt(spinnerSelection);
        Intent newActivity = new Intent(this, IniActivity.class);
        newActivity.putExtra("threadNumber", thSelection);
        startActivity(newActivity);
    }

    public void goto_OperationSUM(View v){
        String spinnerSelection = spn_selection.getSelectedItem().toString();
        int thSelection = Integer.parseInt(spinnerSelection);
        Intent newActivity = new Intent(this, SumActivity.class);
        newActivity.putExtra("threadNumber", thSelection);
        startActivity(newActivity);
    }

}