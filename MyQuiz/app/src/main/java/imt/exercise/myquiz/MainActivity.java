package imt.exercise.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DBSetter database = null;
    private ArrayList<Question> listHandler = null;
    private Question handler = null;
    private int rightCounter = 0;
    private int wrongCounter = 0;
    private int currentIndex = 0;
    private int limitIndex = 5;
    //objs in layout
    private TextView textQuestion = null;
    private TextView textRight = null;
    private TextView textWrong = null;
    private Button btnTrue = null;
    private Button btnFalse = null;
    private Button btnBack = null;
    private Button btnNext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutSetter();
        listHandler = new ArrayList<Question>();
        database = new DBSetter();
        for (int i = 0; i < limitIndex; i++){
            listHandler.add(database.getRandomicalQuestion());
            listHandler.get(i).setNumberOfQuestion(i+1);
        }
        currentIndex = 0;
        handler = listHandler.get(currentIndex);
        setQuestion(handler);
        setListeners();
    }

    private class DBSetter{
        ArrayList<Question> questions = null;
        public DBSetter(){
            questions = new ArrayList<Question>();
            inizializeQuestions();
        }
        public void inizializeQuestions(){
            questions.add(new Question("Is Google the first search engine in Internet?", 1, false));
            questions.add(new Question("Is 64 the number of bit used by the IPv6 address?", 2, false));
            questions.add(new Question("Is Java the programming language used to create programs like applets?", 3, true));
            questions.add(new Question("Is Creeper Virus the first computer virus?", 4, true));
            questions.add(new Question("Is C the programming language exclusively used for artificial intelligence?", 5, false));
            questions.add(new Question("Firewall in computer is used for monitoring.", 6, false));
            questions.add(new Question("DOS is an operative system.", 7, true));
            questions.add(new Question("Sybase is a database management software.", 8, true));
            questions.add(new Question("The number of layers in the OSI model is 5.", 9, false));
            questions.add(new Question("1024 bit is equal to 128 byte.", 10, true));
            /*
            * ADD HERE NEW QUESTIONS!
            * */
        }
        public Question getRandomicalQuestion(){
            Random generator = new Random();
            int takedIndex = generator.nextInt(this.questions.size());
            Question takedQuestion = this.questions.get(takedIndex);
            this.questions.remove(takedIndex);
            return takedQuestion;
        }
    }

    public void layoutSetter(){
        this.textQuestion = findViewById(R.id.Question);
        this.btnTrue = findViewById(R.id.btnTrue);
        this.btnFalse = findViewById(R.id.btnFalse);
        this.btnBack = findViewById(R.id.btnBack);
        this.btnNext = findViewById(R.id.btnNext);
        this.textRight = findViewById(R.id.textCorrect);
        this.textWrong = findViewById(R.id.textWrong);
    }

    public void setQuestion(Question q){
        this.textQuestion.setText(q.getNumberOfQuestion() + ") " + q.getText());
    }

    public void refreshReply(){
        textRight.setText("Correct answers: " + rightCounter);
        textWrong.setText("Wrong answers: " + wrongCounter);
    }

    public void setListeners(){
        //BUTTON TRUE
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = handler.checkIfCorrect(true);
                if (flag) rightCounter += 1;
                else wrongCounter += 1;
                //go to next question
                boolean flagNotDone = false;
                int tempIndex = 0;
                while(!flagNotDone && tempIndex < (limitIndex+1)){
                    if (currentIndex < limitIndex-1) currentIndex++;
                    else currentIndex = 0;
                    tempIndex++;
                    Question tempQuestion = listHandler.get(currentIndex);
                    if (!tempQuestion.getDone()) {
                        flagNotDone = true; //it will exit from cycle
                        handler = tempQuestion;
                        setQuestion(handler);
                    }
                }
                if (!flagNotDone){
                    //Go to new activity

                } else refreshReply();
            }
        });
        //BUTTON FALSE
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = handler.checkIfCorrect(false);
                if (flag) rightCounter += 1;
                else wrongCounter += 1;
                //go to next question
                boolean flagNotDone = false;
                int tempIndex = 0;
                while(!flagNotDone && tempIndex < (limitIndex+1)){
                    if (currentIndex < limitIndex-1) currentIndex++;
                    else currentIndex = 0;
                    tempIndex++;
                    Question tempQuestion = listHandler.get(currentIndex);
                    if (!tempQuestion.getDone()) {
                        flagNotDone = true; //it will exit from cycle
                        handler = tempQuestion;
                        setQuestion(handler);
                    }
                }
                if (!flagNotDone){
                    //Go to new activity
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("CorrectAnswers", rightCounter);
                    intent.putExtra("WrongAnswers", wrongCounter);
                    startActivity(intent);
                } else refreshReply();
            }
        });
        //BUTTON NEXT
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flagNotDone = false;
                int tempIndex = 0;
                while(!flagNotDone && tempIndex < (limitIndex+1)){
                    if (currentIndex < limitIndex-1) currentIndex++;
                    else currentIndex = 0;
                    tempIndex++;
                    Question tempQuestion = listHandler.get(currentIndex);
                    if (!tempQuestion.getDone()) {
                        flagNotDone = true; //it will exit from cycle
                        handler = tempQuestion;
                        setQuestion(handler);
                    }
                }
                if (!flagNotDone){
                    //Go to new activity
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("CorrectAnswers", rightCounter);
                    intent.putExtra("WrongAnswers", wrongCounter);
                    startActivity(intent);
                } else refreshReply();
            }
        });
        //BUTTON BACK
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flagNotDone = false;
                int tempIndex = 0;
                while(!flagNotDone && tempIndex < (limitIndex+1)){
                    if (currentIndex > 0) currentIndex--;
                    else currentIndex = limitIndex-1;
                    tempIndex++;
                    Question tempQuestion = listHandler.get(currentIndex);
                    if (!tempQuestion.getDone()) {
                        flagNotDone = true; //it will exit from cycle
                        handler = tempQuestion;
                        setQuestion(handler);
                    }
                }
                if (!flagNotDone){
                    //Go to new activity
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("CorrectAnswers", rightCounter);
                    intent.putExtra("WrongAnswers", wrongCounter);
                    startActivity(intent);
                } else refreshReply();
            }
        });

    }
}
