package imt.exercise.easycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Texts on the top of the view
    TextView textResult, textNumber, textOperator = null;
    //Number buttons
    Button number_0, number_1, number_2, number_3, number_4, number_5, number_6, number_7,
            number_8, number_9 = null;
    //Operation buttons
    Button operator_add, operator_sub, operator_mult, operator_div = null;
    //Other buttons
    Button buttonCanc, buttonComma, buttonEquals = null;
    //Other variables
    String bufferPrimaryNumber = ""; //It saves the first number
    String bufferSecondaryNumber = ""; //Here it goes the second number of the operation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configure();
        associateListeners();
    }

    private void configure() {
        textResult = findViewById(R.id.field_result);
        textResult.setText("");
        textNumber = findViewById(R.id.field_number);
        textNumber.setText("");
        textOperator = findViewById(R.id.field_operator);
        textOperator.setText("");
        number_0 = findViewById(R.id.numb_0);
        number_1 = findViewById(R.id.numb_1);
        number_2 = findViewById(R.id.numb_2);
        number_3 = findViewById(R.id.numb_3);
        number_4 = findViewById(R.id.numb_4);
        number_5 = findViewById(R.id.numb_5);
        number_6 = findViewById(R.id.numb_6);
        number_7 = findViewById(R.id.numb_7);
        number_8 = findViewById(R.id.numb_8);
        number_9 = findViewById(R.id.numb_9);
        operator_add = findViewById(R.id.op_add);
        operator_sub = findViewById(R.id.op_sub);
        operator_mult = findViewById(R.id.op_mult);
        operator_div = findViewById(R.id.op_div);
        buttonCanc = findViewById(R.id.key_canc);
        buttonComma = findViewById(R.id.key_comma);
        buttonEquals = findViewById(R.id.key_equals);
    }

    private void associateListeners() {

        number_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textOperator.getText().toString().equals("")) {
                    //if (textNumber.getText().toString().length() > 0)
                        concatToNumber("0");
                }
                else {
                    //if (textNumber.getText().toString().length() > 0)
                        concatToBuffer("0");
                }
            }
        });

        number_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")){
                    concatToNumber("1");
                    System.err.println("Printed on number field");
                }
                else {
                    concatToBuffer("1");
                    System.err.println("Concat to buffer");
                }
            }
        });

        number_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")) concatToNumber("2");
                else concatToBuffer("2");
            }
        });

        number_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")) concatToNumber("3");
                else concatToBuffer("3");
            }
        });

        number_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")) concatToNumber("4");
                else concatToBuffer("4");
            }
        });

        number_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")) concatToNumber("5");
                else concatToBuffer("5");
            }
        });

        number_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")) concatToNumber("6");
                else concatToBuffer("6");
            }
        });

        number_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")) concatToNumber("7");
                else concatToBuffer("7");
            }
        });

        number_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")) concatToNumber("8");
                else concatToBuffer("8");
            }
        });

        number_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("")) concatToNumber("9");
                else concatToBuffer("9");
            }
        });

        operator_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the buffer with the secondary number is empty, i can change the operator
                if (bufferSecondaryNumber.equals(""))
                    if (!textNumber.getText().toString().equals("")) {
                        textOperator.setText("+");
                        bufferPrimaryNumber = textNumber.getText().toString();
                    }
            }
        });

        operator_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the buffer with the secondary number is empty, i can change the operator
                if (bufferSecondaryNumber.equals(""))
                    if (!textNumber.getText().toString().equals("")) {
                        textOperator.setText("-");
                        bufferPrimaryNumber = textNumber.getText().toString();
                    }
            }
        });

        operator_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the buffer with the secondary number is empty, i can change the operator
                if (bufferSecondaryNumber.equals(""))
                    if (!textNumber.getText().toString().equals("")) {
                        textOperator.setText("x");
                        bufferPrimaryNumber = textNumber.getText().toString();
                    }
            }
        });

        operator_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If the buffer with the secondary number is empty, i can change the operator
                if (bufferSecondaryNumber.equals(""))
                    if (!textNumber.getText().toString().equals("")) {
                        textOperator.setText("/");
                        bufferPrimaryNumber = textNumber.getText().toString();
                    }
            }
        });

        buttonCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textNumber.setText("");
                textOperator.setText("");
                textResult.setText("");
                bufferPrimaryNumber = "";
                bufferSecondaryNumber = "";
            }
        });

        buttonComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there is no operator, i can write in the number field
                if (textOperator.getText().toString().equals("") && textNumber.getText().toString().length() > 0){
                    //Check if there is a comma character in the number string
                    if (!textNumber.getText().toString().contains(".")){
                        //There is no comma character
                        concatToNumber(".");
                    }
                } else {
                    //Case buffer number
                    if (!bufferSecondaryNumber.contains(".") && bufferSecondaryNumber.length() > 0){
                        //There is no comma character
                        concatToBuffer(".");
                    }
                }
            }
        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Pressed the equals button");
                if (!textNumber.getText().toString().equals(""))
                    if (!textOperator.getText().toString().equals(""))
                        if (!bufferSecondaryNumber.equals("") && !bufferPrimaryNumber.equals(""))
                            if (textResult.getText().toString().equals("")){
                                System.out.println("Allowed to enter in the calculate function");
                                float res = calculate();
                                System.out.println("Result: " + res);
                                textResult.setText(Float.toString(res));
                            }
            }
        });

    }

    private void concatToNumber(String number) {
        String str = textNumber.getText().toString();
        System.err.println("String: " + str);
        if (str.length() < 10){
            str = str.concat(number);
            System.err.println("String with concat: " + str);
        }
        textNumber.setText(str);
    }

    private void concatToBuffer(String number) {
        if (bufferSecondaryNumber.length() < 10) {
            bufferSecondaryNumber = bufferSecondaryNumber.concat(number);
            textNumber.setText(bufferSecondaryNumber);
        }
        System.out.println("New buffer: " + bufferSecondaryNumber);
    }

    private float calculate() {
        System.out.println("We are in the calculate function");
        float firstNumber = Float.parseFloat(bufferPrimaryNumber);
        float secondNumber = Float.parseFloat(bufferSecondaryNumber);
        float resultNumber = 0;
        String operator = textOperator.getText().toString();
        if (operator.equals("+")) resultNumber = firstNumber + secondNumber;
        else if (operator.equals("-")) resultNumber = firstNumber - secondNumber;
        else if (operator.equals("x")) resultNumber = firstNumber * secondNumber;
        else if (operator.equals("/")) resultNumber = firstNumber / secondNumber;
        return resultNumber;
    }

}
