package com.test.cmviewdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private QQStyepView cmMainQQStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initQQStep();
    }

    private void initQQStep() {
        cmMainQQStep = (QQStyepView)findViewById(R.id.main_qqstep);
        //1.设置最大步数
        cmMainQQStep.setMaxStep(4000);
        //2.添加属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float)animation.getAnimatedValue();
                cmMainQQStep.setStepProgress((int) animatedValue);
            }
        });
        valueAnimator.start();
    }
}
