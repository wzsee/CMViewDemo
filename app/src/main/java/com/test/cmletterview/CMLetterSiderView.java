package com.test.cmletterview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.test.cmviewdemo.R;

/**
 * Created by muchen on 2018/1/24.
 */

public class CMLetterSiderView extends View {

    //定义26个字母
    private static String[] cmLetterSiderViewLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    private Paint cmLetterNormalPaint,cmLetterChoosePaint;

    private int cmLetterChooseTextSize;
    private int cmLetterChooseTextColor;

    private int cmLetterNormalTextSize;
    private int cmLetterNormalTextColor;


    public CMLetterSiderView(Context context) {
        this(context,null);
    }

    public CMLetterSiderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CMLetterSiderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //创建画笔
        initPaint(context,attrs);
    }

    /**
     * 创建画笔
     * 1.获取自定义属性
     * 2.创建画笔
     */
    private void initPaint(Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CMLetterSiderView);

        cmLetterNormalTextColor = typedArray.getColor(R.styleable.CMLetterSiderView_cmLetterSiderNormalTextColor,cmLetterNormalTextColor);
        cmLetterNormalTextSize = (int) typedArray.getDimension(R.styleable.CMLetterSiderView_cmLetterSiderNormalTextSize,cmLetterNormalTextSize);

        cmLetterChooseTextColor = typedArray.getColor(R.styleable.CMLetterSiderView_cmLetterSiderChooseTextColor, cmLetterChooseTextColor);
        cmLetterChooseTextSize = (int) typedArray.getDimension(R.styleable.CMLetterSiderView_cmLetterSiderChooseTextSize,cmLetterChooseTextSize);

        typedArray.recycle();

        cmLetterNormalPaint = new Paint();
        cmLetterNormalPaint.setAntiAlias(true);
        cmLetterNormalPaint.setColor(cmLetterNormalTextColor);
        cmLetterNormalPaint.setTextSize(cmLetterNormalTextSize);

        cmLetterChoosePaint = new Paint();
        cmLetterChoosePaint.setAntiAlias(true);
        cmLetterChoosePaint.setColor(cmLetterChooseTextColor);
        cmLetterChoosePaint.setTextSize(cmLetterChooseTextSize);

    }

    /**
     * 重新测量宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        width = getPaddingLeft()+getPaddingLeft()+width;
        height = getPaddingTop()+getPaddingBottom()+height*cmLetterSiderViewLetters.length;

        //重新设置宽高
        setMeasuredDimension(width,height);
    }

    /**
     * 重新绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //重新绘制

        //获取单个字母条目的高度
        int itemsHeight = (getHeight() - getPaddingBottom() - getPaddingTop())/cmLetterSiderViewLetters.length;
        //for循环画出26个字母
        for (int i = 0; i < cmLetterSiderViewLetters.length; i++){

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
