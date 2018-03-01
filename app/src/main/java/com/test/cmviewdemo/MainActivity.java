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

import com.test.cmQQSlidingMenuView.CMQQSlidingMenuActivity;
import com.test.cmSlidingMenuView.CMSlidingMenuActivity;
import com.test.cmfragment.CMViewPageActivity;
import com.test.cmletterview.CMLetterSiderViewActivity;
import com.test.cmtablayoutview.CMTabLayoutViewActivity;
import com.test.cmtouch.CMTouchViewActivity;

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

        initProgressView();

        initCMShapeView();

        startCMLetterSiderViewActivity();

        startCMTabLayoutActivity();

        startCMTouchViewActivity();

        startCMSlidingMenuActivity();

        startCMQQSlidingMenuActivity();


    }


    private void initViewPager() {
        Button main_viewpager_bt = findViewById(R.id.main_viewpager_bt);
        main_viewpager_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CMViewPageActivity.class);
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


    private void initProgressView() {
        Button main_progress_bt = findViewById(R.id.main_cmProgress_start_bt);
        final CMProgressView main_progress_cmProgressView = findViewById(R.id.main_cmProgress_cmProgressView);
        main_progress_cmProgressView.setProgressMas(150);
        main_progress_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 90);
                valueAnimator.setDuration(2000);
                valueAnimator.setInterpolator(new DecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        main_progress_cmProgressView.setProgressCurrent((int) animatedValue);
                    }
                });
                valueAnimator.start();
            }
        });
    }


    private void initCMShapeView() {
        final CMShapeView main_CMShapeView_CMShapeView = findViewById(R.id.main_cmShape_CMShape);
        Button main_CMShapeView_bt = findViewById(R.id.main_cmShape_bt);
        main_CMShapeView_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    main_CMShapeView_CMShapeView.exChange();
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });

    }

    private void startCMLetterSiderViewActivity() {

        Button cmLetterSiderActivityBt = findViewById(R.id.main_cmlettersider_bt);

        cmLetterSiderActivityBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CMLetterSiderViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startCMTabLayoutActivity() {

        Button cmTabLayoutActivityBt = findViewById(R.id.main_cmtablabyout_bt);

        cmTabLayoutActivityBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CMTabLayoutViewActivity.class);
                startActivity(intent);
            }
        });
    }


    private void startCMTouchViewActivity() {

        Button cmTouchViewDemo = findViewById(R.id.main_cmtouchview_bt);

        cmTouchViewDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CMTouchViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startCMSlidingMenuActivity() {

        Button cmSlidingMenuDemo = findViewById(R.id.main_cmslidingmenuview_bt);

        cmSlidingMenuDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CMSlidingMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startCMQQSlidingMenuActivity() {

        Button cmQQSlidingMenuDemo = findViewById(R.id.main_cmqqslidingmenuview_bt);

        cmQQSlidingMenuDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CMQQSlidingMenuActivity.class);
                startActivity(intent);
            }
        });
    }

}
