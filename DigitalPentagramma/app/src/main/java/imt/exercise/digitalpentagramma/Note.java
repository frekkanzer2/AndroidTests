package imt.exercise.digitalpentagramma;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Note {
    private int x, y = 0;

    public Note (int x, int y){
        this.x = x;
        this.y = y;
    }

    public void tryDraw(Canvas c, Paint myPaint){
        c.drawCircle(x, y, 10, myPaint);
        c.drawLine(x+10, y+7, x+10, y-50, myPaint);
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

}
