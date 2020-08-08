package com.example.atish_sbnri;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atish_sbnri.view.DataFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if there is a saved instance (for cases of orientation change)
        if (savedInstanceState != null) return;

        //Load and initialize new fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new DataFragment()).commit();
    }
}