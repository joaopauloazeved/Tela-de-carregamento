package com.example.avaliao;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProgressView extends View {
    private int progressColor;
    private int trackColor;
    private int textColor;
    private int maxValue;
    private int progress;
    private String titleText;
    private boolean modoPercentual = true;

    public ProgressView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressViewAttributes, 0, 0);
        try {
            progressColor = a.getColor(R.styleable.ProgressViewAttributes_progressColor, Color.GREEN);
            trackColor = a.getColor(R.styleable.ProgressViewAttributes_trackColor, Color.LTGRAY);
            textColor = a.getColor(R.styleable.ProgressViewAttributes_textColor, Color.BLACK);
            maxValue = a.getInt(R.styleable.ProgressViewAttributes_maxValue, 10);
            progress = a.getInt(R.styleable.ProgressViewAttributes_progress, 0);
            titleText = a.getString(R.styleable.ProgressViewAttributes_titleText);
            if (titleText == null) titleText = "Progresso";
        }
        finally {
            a.recycle();
        }
        setOnClickListener(v -> {
            modoPercentual = !modoPercentual;
            invalidate();
        });

    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        float raio = Math.min(cx, cy) * 0.8f;
        float espessura = raio * 0.2f;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(espessura);

        paint.setColor(trackColor);
        canvas.drawCircle(cx, cy, raio, paint);

        float angulo = ((float) progress / maxValue) * 360;
        paint.setColor(progressColor);
        android.graphics.RectF oval = new android.graphics.RectF(cx - raio, cy - raio, cx + raio, cy + raio);
        canvas.drawArc(oval, -90, angulo, false, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextSize(raio * 0.4f);
        paint.setTextAlign(Paint.Align.CENTER);

        String textoCentral;
        if (modoPercentual) {
            int porcentagem = maxValue > 0 ? (int)((float) progress / maxValue * 100) : 0;
            textoCentral = porcentagem + "%";
        } else {
            textoCentral = progress + "/" + maxValue;
        }
        canvas.drawText(textoCentral, cx, cy, paint);

        paint.setTextSize(raio * 0.25f);
        canvas.drawText(titleText, cx, cy + raio * 0.4f, paint);
    }

    public void setProgress(int value){
        this.progress = value;
        invalidate();
    }
    public void setMaxValue(int value) {
        this.maxValue = value;
        invalidate();
    }
}
