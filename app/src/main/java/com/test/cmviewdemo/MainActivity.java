package com.test.cmviewdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private QQStyepView cmMainQQStep;
    private ColorTrackTextView colorTrackTextView;
    private Button leftToRight;
    private Button rightToLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initQQStep();

        initColorTrackView();

    }


    private void initColorTrackView() {
        colorTrackTextView = findViewById(R.id.main_TrackTextView);

        leftToRight = findViewById(R.id.main_left_to_right);
        rightToLeft = findViewById(R.id.main_right_to_left);

        leftToRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(2000);
                valueAnimator.setInterpolator(new DecelerateInterpolator());

                colorTrackTextView.setDirectory(ColorTrackTextView.Directory.LEFT_TO_RIGHT);

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        colorTrackTextView.setCurrentProgress(animatedValue);
                    }
                });

                valueAnimator.start();
            }
        });

        rightToLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(2000);
                valueAnimator.setInterpolator(new DecelerateInterpolator());

                colorTrackTextView.setDirectory(ColorTrackTextView.Directory.RIGHT_TO_LEFT);

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        colorTrackTextView.setCurrentProgress(animatedValue);
                    }
                });

                valueAnimator.start();
            }
        });
    }

    private void initQQStep() {
        cmMainQQStep = findViewById(R.id.main_qqstep);
        //1.设置最大步数
        cmMainQQStep.setMaxStep(4000);
        //2.添加属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3369);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                cmMainQQStep.setStepProgress((int) animatedValue);
            }
        });
        valueAnimator.start();
    }
}
