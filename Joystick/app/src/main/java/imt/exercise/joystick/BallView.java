package imt.exercise.joystick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;

public class BallView extends View {

    private Integer size, x, y;
    private Paint painter = new Paint();

    public BallView(Context ctx, Integer x, Integer y, Integer size){
        super(ctx);
        this.x = x;
        this.y = y;
        this.size = size;
        painter.setColor(Color.CYAN);
        painter.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(this.x, this.y, this.size, this.painter);
    }

    public Integer getSize(){
        return this.size;
    }

    public void traslate(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public void moveX(Integer movement, Boolean positive){
        if (positive) {
            if (checkCollision_Right(movement, (FrameLayout) this.getParent()))
                this.x += movement;
            else moveWithDiff((FrameLayout) this.getParent(), "RIGHT");
        }
        else {
            if (checkCollision_Left(movement, (FrameLayout) this.getParent()))
                this.x -= movement;
            else moveWithDiff((FrameLayout) this.getParent(), "LEFT");
        }
    }

    public void moveY(Integer movement, Boolean positive){
        if (positive) {
            if (checkCollision_Down(movement, (FrameLayout) this.getParent()))
                this.y += movement;
            else moveWithDiff((FrameLayout) this.getParent(), "DOWN");
        }
        else {
            if (checkCollision_Up(movement, (FrameLayout) this.getParent()))
                this.y -= movement;
            else moveWithDiff((FrameLayout) this.getParent(), "UP");
        }
    }

    private boolean checkCollision_Up(Integer movement, FrameLayout container){
        if (this.y - this.size - movement >= 0) return true;
        else return false;
    }

    private boolean checkCollision_Down(Integer movement, FrameLayout container){
        if (this.y + this.size + movement <= container.getHeight()) return true;
        else return false;
    }

    private boolean checkCollision_Left(Integer movement, FrameLayout container){
        if (this.x - this.size - movement >= 0) return true;
        else return false;
    }

    private boolean checkCollision_Right(Integer movement, FrameLayout container){
        if (this.x + this.size + movement <= container.getWidth()) return true;
        else return false;
    }

    private void moveWithDiff(FrameLayout container, String direction){
        Integer diff = 0;
        switch(direction){
            case "RIGHT":
                diff = container.getWidth() - this.x;
                this.x += diff;
                this.x -= this.size;
                break;
            case "LEFT":
                this.x = this.size;
                break;
            case "UP":
                this.y = this.size;
                break;
            case "DOWN":
                diff = container.getHeight() - this.y;
                this.y += diff;
                this.y -= this.size;
                break;
        }
        this.invalidate();
    }

}
