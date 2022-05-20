package com.example.E_Graphics_and_Learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class activity_register extends AppCompatActivity {
    LinearLayout framelayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        framelayout = findViewById(R.id.register_layout);
        setFragment(new SignInFragment());
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(framelayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}