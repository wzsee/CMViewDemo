package com.test.cmMaiMaiImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.test.cmUtils.CMBitMapUtils;
import com.test.cmUtils.DpToPxUtil;
import com.test.cmviewdemo.R;

public class CMMaiMaiImageView extends View {

    private static final float IMAGE_WIDTH = DpToPxUtil.dp2px(400);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;


    private float originOffsetX;
    private float originOffsetY;

    private float smallScalab;
    private float bigScalab;

    public CMMaiMaiImageView(Context context) {
        this(context,null);
    }

    public CMMaiMaiImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CMMaiMaiImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //添加绘制图片
        mBitmap = CMBitMapUtils.getAvatarWithImage(getResources(),(int) IMAGE_WIDTH, R.drawable.kenan);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originOffsetX = ((float)(getWidth() - mBitmap.getWidth())) / 2f;
        originOffsetY = ((float)(getHeight() - mBitmap.getHeight())) / 2f;

        if( ((float)mBitmap.getWidth()/mBitmap.getHeight()) > ((float) mBitmap.getHeight()/mBitmap.getWidth())){
            //图片宽度比高度大，横向图片

        }else{
            //图片宽度比高度小，纵向图片
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap,0,(getHeight() - mBitmap.getHeight())/2,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
