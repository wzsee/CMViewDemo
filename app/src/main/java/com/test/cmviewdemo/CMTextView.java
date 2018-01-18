package com.test.cmviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by CMMY on 2018/1/19.
 */

public class CMTextView extends TextView {


    private int mCmTextColor;
    private String mCmText;
    private int mCmTextSiZe;

    private Paint cmPaint;

    public CMTextView(Context context) {
        this(context,null);
    }

    public CMTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CMTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //1.获取自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CMTextView);
        mCmText = typedArray.getString(R.styleable.CMTextView_cmText);
        mCmTextColor = typedArray.getColor(R.styleable.CMTextView_cmTextColor,mCmTextColor);
        mCmTextSiZe = typedArray.getDimensionPixelOffset(R.styleable.CMTextView_cmTextSize,mCmTextSiZe);
        //回收
        typedArray.recycle();

        cmPaint = new Paint();
        cmPaint.setAntiAlias(true);
        cmPaint.setColor(mCmTextColor);
        cmPaint.setTextSize(mCmTextSiZe);
    }

    /**
     * View 的 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //1. 获取 自定义 View 的宽度，高度 的模式
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        if(MeasureSpec.AT_MOST == heigthMode){
            Rect bounds = new Rect();
            cmPaint.getTextBounds(mCmText,0,mCmText.length(),bounds);
            height = bounds.height();
        }

        if(MeasureSpec.AT_MOST == widthMode){
            Rect bounds = new Rect();
            cmPaint.getTextBounds(mCmText,0,mCmText.length(),bounds);
            width = bounds.width();
        }

    }
}
