package com.mad.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textToManipulate;
    private Button changeSize, changeColor;
    private int fontSize = 24, colorPos = 0;
    private final int[] COLORS = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.BLACK};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToManipulate = findViewById(R.id.textToManipulate);
        changeSize = findViewById(R.id.changeSize);
        changeColor = findViewById(R.id.changeColor);

        changeSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontSize += 4;
                textToManipulate.setTextSize(fontSize);
                if (fontSize > 50){
                    fontSize = 24;
                }
            }
        });

        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToManipulate.setTextColor(COLORS[colorPos]);
                colorPos = (colorPos + 1) % COLORS.length;
            }
        });
    }
}