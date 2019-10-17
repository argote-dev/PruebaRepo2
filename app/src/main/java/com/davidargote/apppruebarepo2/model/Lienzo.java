package com.davidargote.apppruebarepo2.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.provider.DocumentsContract;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



public class Lienzo extends View {

    private Path path;
    private Paint paint, canvasPaint;
    private int color=0x0FF000000;

    private Canvas canvas;
    private Bitmap canvasBit;


    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        path = new Path();
        paint = new Paint();

        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(canvasBit,0,0,canvasPaint);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);

                break;

            case  MotionEvent.ACTION_UP:
                path.lineTo(x,y);
                canvas.drawPath(path,paint);
                path.reset();
                break;
        }
        invalidate();
        return true;
    }
}
