package imt.exercise.myquiz;

public class Question {

    private String text;
    private int numberOfQuestion;
    private boolean answer;
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
        this.done = true;
        if (this.answer == userAnswer) return true;
        else return false;
    }

    public boolean getDone(){
        return this.done;
    }

}
