package imt.exercise.myquiz;

public class Question {

    private String text = null;
    private int numberOfQuestion = 0;
    private boolean answer = false;
    private boolean done = false;

    public Question(String text, int number, boolean answer){
        this.text = text;
        this.numberOfQuestion = number;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public void setText(String newValue){
        this.text = newValue;
    }

    public int getNumberOfQuestion(){
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int newValue){
        numberOfQuestion = newValue;
    }

    public boolean getAnswer(){
        return answer;
    }

    public void setAnswer(boolean newValue){
        this.answer = newValue;
    }

    public boolean checkIfCorrect(boolean userAnswer){
        if (this.answer == userAnswer && this.done == false) {
            this.done = true;
            return true;
        }
        else return false;
    }

    public boolean getDone(){
        return this.done;
    }

}
