package com.test.cmPieChartView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.cmUtils.DpToPxUtil;

public class CMPieChartView extends View {

    //半径
    private static final float RADIOUS = DpToPxUtil.dp2px(160);
    //偏移量
    private static final float LENGTH = DpToPxUtil.dp2px(20);
    //偏移的扇形
    private static final int PULLED_OUT_INDEX = 4;

    //画笔
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF bounds = new RectF();
    private int[] angles = {50,120,20,40,90,60};
    private int[] colors = {Color.parseColor("#CE0000"),Color.parseColor("#006030"),Color.parseColor("#5B4B00"),Color.parseColor("#336666"),Color.parseColor("#000079"),Color.parseColor("#3C3C3C")};

    private int currentAngle = 0;

    public CMPieChartView(Context context) {
        super(context);
    }

    public CMPieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CMPieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set(getWidth()/2 - RADIOUS,getHeight()/2 - RADIOUS,getWidth()/2 + RADIOUS,getHeight()/2 + RADIOUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画扇形
        for (int i = 0; i < angles.length; i++){
            //设置画笔颜色
            paint.setColor(colors[i]);
            canvas.save();
            //绘制扇形
            if(i == PULLED_OUT_INDEX){
             canvas.translate((float)Math.cos(Math.toRadians(currentAngle + angles[i]/2))*LENGTH,(float) Math.sin(Math.toRadians(currentAngle + angles[i]/2))*LENGTH);
            }
            canvas.drawArc(bounds,currentAngle,angles[i],true,paint);
            canvas.restore();
            currentAngle += angles[i];

        }

    }
}
