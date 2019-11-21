package imt.exercise.multitouchexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CircleTouch extends View {

    private int id, x, y, size;
    private Paint borderPainter, fillPainter;

    public CircleTouch(Context c, int border, int id){
        super(c);
        this.id = id;
        this.x = this.y = this.size = 0;
        borderPainter = new Paint();
        //Style.STROKE sets a border line
        borderPainter.setStyle(Paint.Style.STROKE);
        borderPainter.setColor(Color.argb(150, 255, 0, 0));
        borderPainter.setStrokeWidth(border);
        fillPainter = new Paint();
        //Style.FILL fills the circle
        fillPainter.setStyle(Paint.Style.FILL);
        fillPainter.setColor(Color.argb(200, 0, 100, 100));
    }

    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setSize(int size){
        this.size = size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, size, borderPainter);
        canvas.drawCircle(x, y, size, fillPainter);
    }

}
