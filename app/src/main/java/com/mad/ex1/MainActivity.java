package com.mad.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText num1, num2;
    private Button add_btn, sub_btn, clear_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        add_btn = findViewById(R.id.add_btn);
        sub_btn = findViewById(R.id.sub_btn);
        clear_btn = findViewById(R.id.clear_btn);

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1.setText("");
                num2.setText("");
                Toast.makeText(MainActivity.this, "Cleared.", Toast.LENGTH_SHORT).show();
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float val1, val2;
                val1 = Float.parseFloat(num1.getText().toString());
                val2 = Float.parseFloat(num2.getText().toString());

                Toast.makeText(MainActivity.this, "Sum = " + (val1 + val2), Toast.LENGTH_SHORT).show();
            }
        });

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float val1, val2;
                val1 = Float.parseFloat(num1.getText().toString());
                val2 = Float.parseFloat(num2.getText().toString());

                Toast.makeText(MainActivity.this, "Difference = " + (val1 - val2), Toast.LENGTH_SHORT).show();
            }
        });
    }
}