package com.bill.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import com.common.base.BaseRelativeLayout;

/**
 * Created by E on 2017/4/20.
 */
public class ChartView extends BaseRelativeLayout {

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            render(canvas);
        } catch (Exception e) {
            Log.e("ilog" ,"-----------onDraw-exception-------------------");
        }
    }

    /**
     * 绘制图表
     *
     * @param canvas 画布
     */
    protected void render(Canvas canvas) {

    }

    /**
     * 刷新图表
     */
    public void refreshChart(){
        this.invalidate();
    }

    private int measureWidth(int measureSpec) {
        int result = 100;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) { //fill_parent
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) { //wrap_content
            result = Math.min(result, specSize);
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 100;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) { //fill_parent
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) { //wrap_content
            result = Math.min(result, specSize);
        }
        return result;
    }
}
