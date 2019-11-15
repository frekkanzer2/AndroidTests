package imt.exercise.digitalpentagramma;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class Pentagramma extends View {

    public int w = 0;
    public int h = 0;
    public int LINESPACING = 0;
    public int FRICTION = 0;
    public int margin_top = 0;
    public int margin_left = 0;
    public ArrayList<Note> listOfNotes = null;

    public Pentagramma(Context c, int w, int margin_top, int margin_left){
        super(c);
        this.LINESPACING = 25;
        this.FRICTION = 25;
        this.w = w;
        this.h = margin_top + (LINESPACING * 5) + FRICTION;
        this.margin_left = margin_left;
        this.margin_top = margin_top;
        this.listOfNotes = new ArrayList<>();
        setMinimumWidth(w);
        setMinimumHeight(h);
    }

    @Override
    public void onMeasure(int w, int h){
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    @Override
    public void onDraw(Canvas c){
        int h = margin_top;
        int x_start = margin_left;
        int x_end = margin_left + this.w;
        Paint myPaint = new Paint();
        myPaint.setColor(Color.BLACK);
        myPaint.setStrokeWidth(5);
        for (int i = 0; i < 5; i++){
            c.drawLine(x_start, h, x_end, h, myPaint);
            h = h + LINESPACING;
        }
        for (int k = 0; k < listOfNotes.size(); k++){
            listOfNotes.get(k).tryDraw(c, myPaint);
        }
    }

    public void addNote(Note myNote){
        listOfNotes.add(myNote);
    }

}
