package com.test.cmStockPieChartView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.cmUtils.DpToPxUtil;

public class cmStockPieChartView extends View {

    //画笔
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //扇形半径
    private static final float RADIOUS = DpToPxUtil.dp2px(120);
    //扇形所在的矩形区域
    private RectF mRectF = new RectF();
    //起始角度值
    private int currentAngle = 270;
    //每块扇形所对应的角度
    private int[] angles = {60,120,120,60};
    //每块扇形所对应的颜色
    private int[] colors = {Color.parseColor("#ea0200"),Color.parseColor("#f96362"),Color.parseColor("#85c263"),Color.parseColor("#5e9e3e")};


    //扇形的偏移
    private static final float PULLED_OUT_INDEX = DpToPxUtil.dp2px(4);
    private static final float IMAGINARY_LINE_WIDTH = DpToPxUtil.dp2px(20);
    //偏移的扇形所在的矩形
    private RectF mRectF_Remove = new RectF();


    private DashPathEffect mDashPathEffect;

    private Path mPath = new Path();

    {
        mDashPathEffect = new DashPathEffect(new float[]{IMAGINARY_LINE_WIDTH, IMAGINARY_LINE_WIDTH,IMAGINARY_LINE_WIDTH,IMAGINARY_LINE_WIDTH}, 0);
    }


    public cmStockPieChartView(Context context) {
        super(context);
    }

    public cmStockPieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public cmStockPieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(getWidth()/2 - RADIOUS,getHeight()/2 - RADIOUS,getWidth()/2 + RADIOUS,getHeight()/2 + RADIOUS);
        mRectF_Remove.set(getWidth()/2 - RADIOUS - PULLED_OUT_INDEX,getHeight()/2 - RADIOUS - PULLED_OUT_INDEX,getWidth()/2 + RADIOUS + PULLED_OUT_INDEX,getHeight()/2 + RADIOUS + PULLED_OUT_INDEX);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制扇形
        mPaint.reset();
        for (int i = 0;i < angles.length; i++){
            mPaint.setColor(colors[i]);
            canvas.save();
            //平移绿色，红色扇形
            if(i == 0 || i == 3){
                canvas.translate(0, -PULLED_OUT_INDEX);
                //绘制绿色与红色扇形
                canvas.drawArc(mRectF_Remove,currentAngle,angles[i],true,mPaint);
            }else{
                //绘制扇形
                canvas.drawArc(mRectF,currentAngle,angles[i],true,mPaint);
            }
            canvas.restore();
            currentAngle += angles[i];
        }

        //绘制虚线
        mPaint.setPathEffect(mDashPathEffect);
        mPaint.setColor(Color.BLACK);
        //红色扇形上的虚线
        canvas.drawLine(getWidth()/2 ,getHeight()/2 - PULLED_OUT_INDEX,getWidth()/2 + (float) Math.sin(30)*(RADIOUS + PULLED_OUT_INDEX),(getHeight()/2 - PULLED_OUT_INDEX) - (float) Math.sin(30)*(RADIOUS + PULLED_OUT_INDEX),mPaint);
//        mPath.moveTo(getWidth()/2 ,getHeight()/2 - PULLED_OUT_INDEX);
//        mPath.lineTo(getWidth()/2 + (float) Math.cos(angles[0]/2)*(RADIOUS + PULLED_OUT_INDEX),(float) getHeight()/2 + (float) Math.sin(angles[0]/2)*(RADIOUS + PULLED_OUT_INDEX));
//        canvas.drawPath(mPath,mPaint);
        //绿色扇形上的虚线
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(getWidth()/2 ,getHeight()/2 - PULLED_OUT_INDEX,getWidth()/2 + (float) Math.cos(30)*(RADIOUS + PULLED_OUT_INDEX),(getHeight()/2 - PULLED_OUT_INDEX) - (float) Math.sin(30)*(RADIOUS + PULLED_OUT_INDEX),mPaint);
        mPaint.setPathEffect(null);
    }
}
