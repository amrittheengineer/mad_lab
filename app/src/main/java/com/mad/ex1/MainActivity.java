package com.mad.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static class MyView extends View{

        public MyView(Context context) {
            super(context);
        }

        Paint paint = new Paint();

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            paint.setTextSize(40);

            paint.setColor(Color.GREEN);
            canvas.drawText("Circle", 55, 30, paint);

            paint.setColor(Color.RED);
            canvas.drawCircle(100, 150,100, paint);

            paint.setColor(Color.GREEN);
            canvas.drawText("Rectangle", 255, 30, paint);

            paint.setColor(Color.YELLOW);
            canvas.drawRect(250, 50,400,350, paint);

            paint.setColor(Color.GREEN);
            canvas.drawText("SQUARE", 55, 430, paint);

            paint.setColor(Color.BLUE);
            canvas.drawRect(50, 450,150,550, paint);

            paint.setColor(Color.GREEN);
            canvas.drawText("LINE", 255, 430, paint);

            paint.setColor(Color.RED);
            canvas.drawLine(250, 500, 350, 500, paint);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}