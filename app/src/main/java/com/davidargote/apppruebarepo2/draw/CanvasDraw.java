package com.davidargote.apppruebarepo2.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;


public class CanvasDraw extends View {

    private Path drawPath;
    private Paint paint, canvasPaint;
    private int paintColor = 000000;
    private Canvas canvas;
    private Bitmap canvasBitMap;
    float lapisSize;
    public static boolean borrador = false;

    public CanvasDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        startDrawing();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitMap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, paint);
    }

    private void startDrawing() {
        drawPath = new Path();
        paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        paint.setAntiAlias(true);
        lapisSize = 10;
        paint.setStrokeWidth(lapisSize);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(x, y);
                canvas.drawPath(drawPath, paint);
                drawPath.reset();
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(canvasBitMap);
    }

    public void setBorrado(boolean estado){
        borrador = estado;
        if(borrador) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }else{
            paint.setXfermode(null);
        }
    }



}

