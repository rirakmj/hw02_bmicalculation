package com.cookandroid.hw02_bmicalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    EditText etHeight, etWeight;
    RadioButton rbtnFemale, rbtnMale;
    Spinner spinnerBlood;
    CheckBox btnDrinking, btnSmoking, btnWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("신체 질량 지수 측정");

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        rbtnFemale = findViewById(R.id.rbtnFemale);
        rbtnMale = findViewById(R.id.rbtnMale);
        etHeight = findViewById(R.id.etHeight);
        etHeight = findViewById(R.id.etHeight);

    }

}