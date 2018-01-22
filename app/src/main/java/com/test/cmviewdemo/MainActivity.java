package com.test.cmviewdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.cmfragment.CMViewPageActivity;

public class MainActivity extends AppCompatActivity {

    private QQStyepView cmMainQQStep;
    private ColorTrackTextView colorTrackTextView;
    private Button leftToRight;
    private Button rightToLeft;
    private Button main_step_bt;
    private EditText main_step_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initQQStep();

        initColorTrackView();

        initViewPager();

    }


    private void initViewPager() {
        Button main_viewpager_bt = findViewById(R.id.main_viewpager_bt);
        main_viewpager_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(MainActivity.this,CMViewPageActivity.class);
                startActivity(intent);
            }
        });
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
        main_step_et = findViewById(R.id.main_qqstep_et);
        final String currentText = main_step_et.getText().toString();
        main_step_bt = findViewById(R.id.main_qqstep_bt);
        cmMainQQStep = findViewById(R.id.main_qqstep);
        //1.设置最大步数
        cmMainQQStep.setMaxStep(4000);

        main_step_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(main_step_et == null){
//                    Toast.makeText(getApplicationContext(),"请输入步数",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                //2.添加属性动画
                ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3269);
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
        });
    }
}
