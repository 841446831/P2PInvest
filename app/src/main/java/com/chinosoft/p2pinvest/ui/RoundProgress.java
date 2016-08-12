package com.chinosoft.p2pinvest.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.chinosoft.p2pinvest.R;

/**
 * Created by cai on 2016/7/28.
 */
public class RoundProgress extends View{

    private Paint paint = new Paint();
    Paint paintText = new Paint();
    Paint paintTextName = new Paint();
    Paint paintTextTitle = new Paint();

    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int textTitleColor;
    private int textNameColor;
    private float textTitleSize;
    private float textNameSize;
    private String textTitle = "";

    private int max = 100;
    private int progress = 80;

    private String name = "";

    public RoundProgress(Context context) {
        super(context);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        //圆环的颜色
        roundColor = mTypedArray.getColor(R.styleable.RoundProgress_roundColor, Color.RED);
        //圆环进度的颜色
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgress_roundProgressColor, Color.GREEN);
        //中间进度百分比文字字符串的颜色
        textColor = mTypedArray.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);
        //中间进度百分比的字符串的字体大小
        textSize = mTypedArray.getDimension(R.styleable.RoundProgress_textSize, 15);
        //圆环的宽度
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgress_roundWidth, 5);

        textTitleColor = mTypedArray.getColor(R.styleable.RoundProgress_textTitleColor, Color.GREEN);
        //中间进度百分比的字符串的字体大小
        textTitleSize = mTypedArray.getDimension(R.styleable.RoundProgress_textTitleSize, 15);

        textNameColor = mTypedArray.getColor(R.styleable.RoundProgress_textNameColor, Color.GREEN);
        //中间进度百分比的字符串的字体大小
        textNameSize = mTypedArray.getDimension(R.styleable.RoundProgress_textNameSize, 15);

        //textTitle = mTypedArray.getString(R.styleable.RoundProgress_textTitle);
        mTypedArray.recycle();
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //第一步：绘制一个最外层的圆
        paint.setColor(roundColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        int center = getWidth() / 2;
        //Log.i("TAG","center--->" + center);
        int radius = (int) (center - roundWidth / 2);
        canvas.drawCircle(center, center, radius, paint);

        //第二步：绘制正中间的文本
        //float textWidth = paint.measureText("12333");
        float textWidth = getTextWidth(paintText,progress + "%");
        //Log.i("TAG","textWidth--->" + textWidth);
        paintText.setColor(textColor);
        paintText.setTextSize(textSize);
        paintText.setStrokeWidth(0);
        canvas.drawText(progress + "%", center - textWidth / 2, center + textSize / 2, paintText);

        //float textNameWidth = paint1.measureText("12333");
        float textNameWidth = getTextWidth(paintTextName,name);
        //Log.i("TAG","textNameWidth--->" + textNameWidth);
        paintTextName.setStyle(Paint.Style.STROKE);
        paintTextName.setColor(textNameColor);
        paintTextName.setTextSize(textNameSize);
        paintTextName.setStrokeWidth(0);
        paintTextName.setAntiAlias(true);
        canvas.drawText(name,center - textNameWidth / 2,center / 2 + textNameSize / 2,paintTextName);

        float textTitleWidth = getTextWidth(paintTextTitle,textTitle);
       // Log.i("TAG","textNameWidth--->" + textTitleWidth);
        paintTextTitle.setStyle(Paint.Style.STROKE);
        paintTextTitle.setColor(textTitleColor);
        paintTextTitle.setTextSize(textTitleSize);
        paintTextTitle.setStrokeWidth(0);
        paintTextTitle.setAntiAlias(true);
        canvas.drawText(textTitle,center - textTitleWidth / 2,center + center / 2 + textTitleSize / 2,paintTextTitle);

        //第三步：
        /**
         * 参数解释：
         * oval：绘制弧形圈所包含的矩形范围轮廓
         * 0：开始的角度
         * 360 * progress / max：扫描过的角度
         * false：是否包含圆心
         * paint：绘制弧形时候的画笔
         */
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        paint.setColor(roundProgressColor);
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval,270,360 * progress / max, false, paint);

    }

    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    public void setProgress(int progress){
        this.progress = progress;
        if(progress>100){
            this.progress = 100;
        }
        postInvalidate();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }
}
