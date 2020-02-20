package imt.exercise.joystick;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MovementFragment extends Fragment {

    private Context ctx;
    private FrameLayout container;
    private BallView objBall;

    public MovementFragment() {
        super();
    }

    @Override
    public void onStart() {
        super.onStart();
        objBall = new BallView(ctx, 200, 200, 60);
        container.addView(objBall);
        container.invalidate();
    }

    @Override
    public void onStop() {
        super.onStop();
        container.removeView(objBall);
        container.invalidate();
        objBall = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_area, container, false);
        this.container = v.findViewById(R.id.container_area);
        ctx = v.getContext();
        return v;
    }

    public void setContainer(FrameLayout frame){
        this.container = frame;
    }

    public void setObjBall(BallView ball){
        this.objBall = ball;
    }

    public FrameLayout getContainer() {
        return container;
    }

    public BallView getObjBall() {
        return objBall;
    }

}