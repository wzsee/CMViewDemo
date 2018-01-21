package com.test.cmviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by CMMY on 2018/1/21.
 */

public class QQStyepView extends View {

    private int cmOuterColor = Color.BLUE;
    private int cmInnerColor = Color.RED;
    private int cmBorderWidth = 20;
    private int cmStepTextSize = 20;
    private int cmStepTextColor = Color.RED;

    public QQStyepView(Context context) {
        this(context,null);
    }

    public QQStyepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQStyepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QQStyepView);
        cmOuterColor = typedArray.getColor(R.styleable.QQStyepView_cmOuterColor,cmOuterColor);
        cmInnerColor = typedArray.getColor(R.styleable.QQStyepView_cmInnerColor,cmInnerColor);
        cmBorderWidth = typedArray.getDimensionPixelOffset(R.styleable.QQStyepView_cmBorderWidth,cmBorderWidth);
        cmStepTextSize = typedArray.getDimensionPixelOffset(R.styleable.QQStyepView_cmStepTextSize,cmStepTextSize);
        cmStepTextColor = typedArray.getColor(R.styleable.QQStyepView_cmStepTextColor,cmStepTextColor);
        typedArray.recycle();

    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取高度，宽度
        int heigth = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //获取高度模式，宽度模式
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        //如果高度的模式与宽度模式都是AT_MOST，则设置默认大小
        if(MeasureSpec.AT_MOST == heigthMode){
            heigth = 40;
        }
        if(MeasureSpec.AT_MOST == widthMode){
            width = 40;
        }
        //重新设置自定义View的宽高，并且保证宽高一致
        setMeasuredDimension(width>heigth?heigth:width,width>heigth?heigth:width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
    }
}
