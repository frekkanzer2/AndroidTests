package imt.exercise.multitouchexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public class Couple{
        public Object first, second;
        public Couple(Object a, Object b){
            this.first = a;
            this.second = b;
        }
    }

    private FrameLayout myScreen;
    private ArrayList<Couple> listOfRecords = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myScreen = findViewById(R.id.myscreen);

        /*
        * DEFAULT GENERATION OF CIRCLES
        * */
        /*
        CircleTouch circle1 = new CircleTouch(getApplicationContext(), 20, 1);
        circle1.setCoordinates(100, 100);
        circle1.setSize(50);
        circle1.invalidate();
        myScreen.addView(circle1);
        */

        //Touchscreen generation
        myScreen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointer_id, pointer_index;
                CircleTouch circle;

                switch(event.getActionMasked()){
                    //PRESSION
                    case MotionEvent.ACTION_DOWN: case MotionEvent.ACTION_POINTER_DOWN:
                        pointer_index = event.getActionIndex();
                        pointer_id = event.getPointerId(pointer_index);
                        CircleTouch item;
                        boolean flag = true;
                        if (listOfRecords.size() == 0) flag = false;
                        else {
                            if (checkContainsID(pointer_id)) flag = true;
                            else flag = false;
                        }
                        System.err.println("Entered");
                        /*
                        * flag = true -> the id of the pointer exists
                        * flag = false -> the id of the pointer does not exists
                        * */
                        if (!flag){
                            System.err.println("Adding a circle");
                            //if the id does not exists, add it on the screen
                            item = new CircleTouch(getApplicationContext(), 20, pointer_id);
                            listOfRecords.add(new Couple(pointer_id, item));
                            myScreen.addView(item);
                        } else {
                            System.err.println("Taking a circle");
                            //item already exists
                            item = (CircleTouch) getFromListByID(pointer_id).second;
                        }
                        item.setCoordinates((int) event.getX(pointer_index), (int) event.getY(pointer_index));
                        item.setSize(30);
                        item.invalidate();
                        break;

                    //RELEASE
                    case MotionEvent.ACTION_UP: case MotionEvent.ACTION_POINTER_UP:
                        System.err.println("Found");
                        pointer_index = event.getActionIndex();
                        pointer_id = event.getPointerId(pointer_index);
                        if (checkContainsID(pointer_id)){

                            CircleTouch circleToRemove = (CircleTouch) getFromListByID(pointer_id).second;
                            circleToRemove.setSize(0);
                            circleToRemove.invalidate();
                            myScreen.removeView(circleToRemove);
                            for (int i = 0; i < listOfRecords.size(); i++){
                                CircleTouch tempCircle = (CircleTouch) listOfRecords.get(i).second;
                                if (tempCircle.equals(circleToRemove)){
                                    listOfRecords.remove(i);
                                    break;
                                }
                            }
                        }
                        break;

                    //MOVING
                    case MotionEvent.ACTION_MOVE:
                        System.err.println("Moving");
                        for (int i = 0; i < event.getPointerCount(); i++){
                            int myPointer = event.getPointerId(i);
                            CircleTouch myCircle = (CircleTouch) getFromListByID(myPointer).second;
                            myCircle.setCoordinates((int) event.getX(i), (int) event.getY(i));
                            myCircle.invalidate();
                        }
                        break;
                }
                return true;
            }
        });
    }

    public boolean checkContainsID(int id) {
        for (Couple c : listOfRecords)
            if (id == (Integer) c.first){
                return true;
            }
        return false;
    }

    public Couple getFromListByID(int id){
        for (Couple c: listOfRecords)
            if (id == (Integer) c.first){
                return c;
            }
        return null;
    }

}
