package imt.exercise.customwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class CustomWidget extends View {

    private int w, h = 0;
    private int color = 0;

    public CustomWidget(Context c, int w, int h){
        super(c);
        this.w = w;
        this.h = h;
        setMinimumWidth(w);
        setMinimumHeight(h);
    }

    @Override
    protected void onMeasure(int w, int h){
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    @Override
    protected void onDraw(Canvas myCanvas){
        myCanvas.drawColor(this.color);
    }

    public void setColor(int newColor){
        this.color = newColor;
    }

}
