package imt.exercise.performancetest.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Logger;

import imt.exercise.performancetest.OtherUtils.ArrayDimension;
import imt.exercise.performancetest.R;
import imt.exercise.performancetest.Threads.InizializationThread;

public class IniActivity extends AppCompatActivity {

    private TextView selThreads = null;
    private TextView txtTime = null;
    private EditText selSize = null;
    private Button btnStart = null;
    private int numbOfThreads = 0;
    private int iniSize = 0;
    private static int MULTIPLIER_MLN = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini);

        selThreads = findViewById(R.id.txt_selection);
        selSize = findViewById(R.id.editTxt_iniSize);
        Intent myIntent = getIntent();
        numbOfThreads = myIntent.getIntExtra("threadNumber", 1);
        selThreads.setText("Selected threads: " + numbOfThreads);

        btnStart = findViewById(R.id.btn_start);
        txtTime = findViewById(R.id.txt_timing);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniSize = Integer.parseInt(selSize.getText().toString());
                int[] myArray = new int[iniSize];
                ArrayList<InizializationThread> thList = new ArrayList<>();
                //Record start time
                long startRecord = System.currentTimeMillis();
                for (int i = 0; i < numbOfThreads; i++){
                    ArrayDimension tempDim;
                    if (i == 0) tempDim = new ArrayDimension(0, iniSize/numbOfThreads);
                    else tempDim = new ArrayDimension(iniSize/i, iniSize/(i+1));
                    InizializationThread tempThread = new InizializationThread(myArray, tempDim, IniActivity.MULTIPLIER_MLN);
                    thList.add(tempThread);
                    tempThread.start();
                }
                for (int i = 0; i < numbOfThreads; i++) {
                    try {
                        thList.get(i).join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Logger log = Logger.getLogger("ExceptionLogger");
                        log.info("Thread interrupted, that should not happens. Exiting from software...");
                        System.exit(1);
                    }
                }
                //Record end time
                long endRecord = System.currentTimeMillis();
                long duration = endRecord - startRecord;

                //Update values:
                txtTime.setText("Timing: " + duration + " msec");
            }
        });
    }



}
