package com.test.cmtablayoutview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muchen on 2018/2/7.
 */

public class CMTableLayoutView extends ViewGroup {


    private List<List<View>> mChildViews = new ArrayList<>();

    public CMTableLayoutView(Context context) {
        this(context,null);
    }

    public CMTableLayoutView(Context context, AttributeSet attrs)  {
        this(context, attrs,0);
    }

    public CMTableLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @Info 指定宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();

        //获取宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 计算高度
        int height = MeasureSpec.getSize(heightMeasureSpec);
        // 一行的宽度
        int lindWidth = getPaddingLeft();

        for (int i = 0; i < childCount ; i++){
            //2.1.1 for循环 测量子View
            View childView = getChildAt(i);
            // 获取子View的宽高，调用子View的onMeasure
            measureChild(childView,widthMeasureSpec,widthMeasureSpec);

            ArrayList<View> childViews = new ArrayList<View>();

            //当所有子View的宽度相加，大于父控件的宽度的时候，执行换行
            if(lindWidth + childView.getMeasuredWidth() > width){
                height += childView.getMeasuredHeight();
                lindWidth = 0;
                childViews = new ArrayList<View>();
                mChildViews.add(childViews);
            }else{
                lindWidth += childView.getMeasuredWidth();
                childViews.add(childView);
            }

        }

        // 根据子View计算和指定自己的宽高
        setMeasuredDimension(width,height);
    }

    /**
     * @info 从新设置布局位置
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();

        int left = 0,top = getPaddingTop(),right = 0,bottom = 0;

        for (List<View> childViews : mChildViews) {

            left = getPaddingLeft();

            for (View childView : childViews) {

                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();

                //摆放
                childView.layout(left,top,right,bottom);

                left += childView.getMeasuredWidth();
            }

            top += childViews.get(0).getMeasuredHeight();
        }
    }
}
