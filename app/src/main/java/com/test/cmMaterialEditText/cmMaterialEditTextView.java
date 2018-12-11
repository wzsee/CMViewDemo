package com.test.cmMaterialEditText;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.test.cmUtils.DpToPxUtil;

public class cmMaterialEditTextView extends AppCompatEditText {

    private static final String TAG = cmMaterialEditTextView.class.getSimpleName();

    private static final float TEXT_SIZE = DpToPxUtil.dp2px(18);
    private static final float TEXT_MARGIN = DpToPxUtil.dp2px(8);
    private static final float TEXT_VERTRICAL_OFFSET = DpToPxUtil.dp2px(28);
    private static final float TEXT_HORIZONTAL_OFFSET = DpToPxUtil.dp2px(5);
    private static final float TEXT_ANIMATION_OFFSET = DpToPxUtil.dp2px(28);

    ObjectAnimator animator;

    boolean floatingLabelShown = false;
    float floatingLabelFraction;

    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    //画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    {
        //设置Padding
        setPadding(getPaddingLeft(),(int) (getPaddingTop() + TEXT_SIZE + TEXT_MARGIN),getPaddingRight(),getPaddingBottom());
        mPaint.setTextSize(TEXT_SIZE);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //隐藏
                if(floatingLabelShown && TextUtils.isEmpty(s)){
                    getAnimator().reverse();
                    floatingLabelShown = false;
                }else if(!floatingLabelShown && !TextUtils.isEmpty(s)){
                    getAnimator().start();
                    floatingLabelShown = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ObjectAnimator getAnimator() {
        if(animator == null){
            animator = ObjectAnimator.ofFloat(cmMaterialEditTextView.this,"floatingLabelFraction",0,1);
        }
        return animator;
    }

    public cmMaterialEditTextView(Context context) {
        super(context);
    }

    public cmMaterialEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public cmMaterialEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAlpha((int) (0xff * floatingLabelFraction));
        float extraOffset = TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction);
        canvas.drawText(getHint().toString(),TEXT_HORIZONTAL_OFFSET,TEXT_VERTRICAL_OFFSET + extraOffset,mPaint);
    }
}
