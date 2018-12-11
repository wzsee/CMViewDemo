package com.test.cmAnimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.cmUtils.CMBitMapUtils;
import com.test.cmUtils.DpToPxUtil;
import com.test.cmviewdemo.R;

public class cmAnimationRorateView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap mBitmap;

    private Camera mCamera = new Camera();

    {
        mBitmap = CMBitMapUtils.getAvatarWithImage(getResources(),(int) DpToPxUtil.dp2px(200),R.drawable.sun_photo);
        mCamera.rotateX(45);
        mCamera.setLocation(0,0,DpToPxUtil.getZForCamera());
    }

    public cmAnimationRorateView(Context context) {
        super(context);
    }

    public cmAnimationRorateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public cmAnimationRorateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate((getWidth()/2),(getHeight()/2));
        canvas.clipRect(-  mBitmap.getWidth()/2,- mBitmap.getHeight()/2,mBitmap.getWidth()/2,0);
        canvas.translate(-(getWidth()/2),-(getHeight()/2));
        canvas.drawBitmap(mBitmap,getWidth()/2 -  mBitmap.getWidth()/2,getHeight()/2 - mBitmap.getHeight()/2,mPaint);
        canvas.restore();



        canvas.save();
        canvas.translate((getWidth()/2),(getHeight()/2));
        mCamera.applyToCanvas(canvas);
        canvas.clipRect( -  mBitmap.getWidth()/2,0,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        canvas.translate(-(getWidth()/2),-(getHeight()/2));
        canvas.drawBitmap(mBitmap,getWidth()/2 -  mBitmap.getWidth()/2,getHeight()/2 - mBitmap.getHeight()/2,mPaint);
        canvas.restore();
    }
}
