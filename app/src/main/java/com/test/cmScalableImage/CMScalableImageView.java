package com.test.cmScalableImage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import com.test.cmUtils.CMBitMapUtils;
import com.test.cmUtils.DpToPxUtil;

public class CMScalableImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

    private static final float IMAGE_WIDTH = DpToPxUtil.dp2px(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap mBitmap;

    private OverScroller mOverScroller;

    //滑动偏移
    private float offsetX;
    private float offsetY;

    //旋转偏移量
    private float originOffsetX;
    private float originOffsetY;

    private float smallScalab;
    private float bigScalab;

    private float scale;

    private boolean isBig;

    private float scaleFraction;
    private ObjectAnimator scaleAnimator;

    public ObjectAnimator getScaleAnimator() {
        if(scaleAnimator == null){
            scaleAnimator = ObjectAnimator.ofFloat(this,"scaleFraction",0,1);
        }
//        scaleAnimator.setFloatValues(smallScalab,bigScalab);
        return scaleAnimator;
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    GestureDetectorCompat mGestureDetectorCompat;

    public CMScalableImageView(Context context) {
        this(context,null);
    }

    public CMScalableImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CMScalableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mBitmap = CMBitMapUtils.getAvatar(getResources(),(int) IMAGE_WIDTH);
        mGestureDetectorCompat = new GestureDetectorCompat(context,this);
        mGestureDetectorCompat.setOnDoubleTapListener(this);
        mOverScroller = new OverScroller(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        originOffsetX = ((float) (getWidth() - mBitmap.getWidth())) / 2;
        originOffsetY = ((float) (getHeight() - mBitmap.getHeight())) / 2;

        if( (float)(mBitmap.getWidth()/mBitmap.getHeight()) > (float)(getWidth()/getHeight())){
            smallScalab = (float) getWidth() / mBitmap.getWidth();
            bigScalab = (float) getHeight() / mBitmap.getHeight() * OVER_SCALE_FACTOR;
        }else{
            bigScalab = (float) getWidth() / mBitmap.getWidth();
            smallScalab = (float) getHeight() / mBitmap.getHeight() * OVER_SCALE_FACTOR;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(offsetX,offsetY);
        scale = smallScalab + (bigScalab - smallScalab) * scaleFraction;
        canvas.scale(scale,scale,getWidth()/2.0f,getHeight()/2.0f);
        canvas.drawBitmap(mBitmap, originOffsetX, originOffsetY,mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return mGestureDetectorCompat.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if(isBig){
            offsetX -= distanceX;

            offsetX = Math.min(offsetX,(mBitmap.getWidth()*bigScalab - getWidth())/2);
            offsetX = Math.max(offsetX, -(mBitmap.getWidth()*bigScalab - getWidth())/2);

            offsetY -= distanceY;
            offsetY = Math.min(offsetY,(mBitmap.getHeight()*bigScalab - getHeight())/2);
            offsetY = Math.max(offsetY, -(mBitmap.getHeight()*bigScalab - getHeight())/2);

            invalidate();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if(isBig){
            mOverScroller.fling((int) offsetX,(int)offsetY,(int)velocityX,(int)velocityY,
                    -(int)(mBitmap.getWidth()*bigScalab - getWidth())/2,
                    (int)(mBitmap.getWidth()*bigScalab - getWidth())/2,
                    -(int)(mBitmap.getHeight()*bigScalab - getHeight())/2,
                    (int)(mBitmap.getHeight()*bigScalab - getHeight())/2);

            postOnAnimation(this);
        }

        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        isBig = !isBig;
        if(isBig){
            getScaleAnimator().start();
        }else{
            getScaleAnimator().reverse();
        }

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public void run() {
        if(mOverScroller.computeScrollOffset()){
            offsetX = mOverScroller.getCurrX();
            offsetY = mOverScroller.getCurrY();
            invalidate();
            postOnAnimation(this);
        }
    }
}
