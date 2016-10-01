package com.kffuck.mikum.clfgo;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class RadarView extends View{

    private Paint mPaintLine;
    private Paint mPaintCircle;
    private Paint mPaintScan;
    private int mWidth, mHeight;

    private static float[] circleProportion = {1 / 13f, 2 / 13f, 3 / 13f, 4 / 13f, 5 / 13f, 6 / 13f};
    private Matrix matrix = new Matrix();
    private int scanAngle;
    private Shader scanShader;
    private Bitmap centerBitmap;

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.rgb(106,232,255));
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStrokeWidth(1);
        mPaintLine.setStyle(Paint.Style.STROKE);

        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.argb(55,206,232,255));
        mPaintCircle.setAntiAlias(true);

        mPaintScan = new Paint();
        mPaintScan.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * circleProportion[1], mPaintLine);     // 绘制小圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * circleProportion[2], mPaintLine);   // 绘制中圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * circleProportion[3], mPaintLine); // 绘制中大圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * circleProportion[4], mPaintLine);  // 绘制大圆
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * circleProportion[5], mPaintLine);  // 绘制大大圆
    }

    private void drawScan(Canvas canvas) {
        canvas.save();
        mPaintScan.setShader(scanShader);
        canvas.concat(matrix);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth * circleProportion[4], mPaintScan);
        canvas.restore();
    }

    private void drawCenterIcon(Canvas canvas) {
        canvas.drawBitmap(centerBitmap, null,
                new Rect((int) (mWidth / 2 - mWidth * circleProportion[0]), (int) (mHeight / 2 - mWidth * circleProportion[0]),
                        (int) (mWidth / 2 + mWidth * circleProportion[0]), (int) (mHeight / 2 + mWidth * circleProportion[0])), mPaintCircle);
    }


}
