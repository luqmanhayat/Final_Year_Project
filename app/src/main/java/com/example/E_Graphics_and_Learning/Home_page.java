package com.example.E_Graphics_and_Learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_page extends AppCompatActivity {
    Button btn1, btn2, btn3 ,btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btn1 = findViewById(R.id.btn_graphics);
        btn2 = findViewById(R.id.btn_motion_graphics);
        btn3 = findViewById(R.id.btn_templats);
        btn4 = findViewById(R.id.btn_customorder);
        btn5 = findViewById(R.id.btn_courses);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Graphic_design_Page.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Motion_Graphic_Design_Page.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Templates_Page.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Custom_Order.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Learn_Graphics_and_Animation_Page.class);
                startActivity(intent);
            }
        });
    }
}