package com.example.adslo.viewflipperexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private ViewFlipper vFlipper;
    private int xPrev;
    private ImageButton btnNext;
    private ImageButton btnPrev;
    private float xAtDown;
    private float xAtUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
    }

    private void setUpUI() {
        vFlipper = (ViewFlipper) findViewById(R.id.vFlipper);
        vFlipper.setOnTouchListener(this);

        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextFlipper();
            }
        });
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevFlipper();
            }
        });
    }

    private void nextFlipper() {
        vFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                R.anim.push_left_in));
        vFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                R.anim.push_right_out));
        vFlipper.showNext();
    }

    private void prevFlipper() {

        vFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                R.anim.push_right_in));
        vFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                R.anim.push_left_out));

        vFlipper.showPrevious();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v != vFlipper)
            return false;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xAtDown = event.getX(); // 터치 시작지점 x좌표 저장

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            xAtUp = event.getX(); // 터치 끝난지점 x좌표 저장

            if (xAtUp < xAtDown) {
                nextFlipper();
            } else if (xAtUp > xAtDown) {
                prevFlipper();
            }
        }
        return true;
    }
}
