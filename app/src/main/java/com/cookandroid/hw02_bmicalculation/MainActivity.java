package com.cookandroid.hw02_bmicalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etHeight, etWeight;
    RadioGroup rGroup;
    RadioButton rbtnFemale, rbtnMale, rbtnChecked;
    Spinner spinnerBlood;
    CheckBox btnDrinking, btnSmoking, btnWorkout;
    Button btnResult;
    TextView textResult1, textResult2;
    String bloodtype, gender, height, weight;
    double bmiResult1, bmiResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("신체 질량 지수 측정");

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        rGroup = (RadioGroup) findViewById(R.id.rGroup);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtnFemale);
        rbtnMale = (RadioButton) findViewById(R.id.rbtnMale);
        btnDrinking = findViewById(R.id.btnDrinking);
        btnSmoking = findViewById(R.id.btnSmoking);
        btnWorkout = findViewById(R.id.btnWorkout);
        btnResult = findViewById(R.id.btnResult);
        textResult1 = findViewById(R.id.textResult1);
        textResult2 = findViewById(R.id.textResult2);

        // 혈액형 스피너 선택
        final String[] blood = { "A", "B", "O", "AB" };
        spinnerBlood = findViewById(R.id.spinnerBlood);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, blood);
        spinnerBlood.setAdapter(adapter);

        // 몸 상태 측정 버튼을 누르면
        // 1. 혈액형과 성별 출력 예) A형 여자입니다.
        // 2. 신체 질량 지수 출력 예) 신체 질량 지수는 21.4입니다.
        btnResult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                bloodtype = spinnerBlood.getSelectedItem().toString();
                rbtnChecked = (RadioButton) findViewById(rGroup.getCheckedRadioButtonId());
                gender = rbtnChecked.getText().toString();

                height = etHeight.getText().toString();
                weight = etWeight.getText().toString();
                bmiResult1 = Double.parseDouble(weight)/((Double.parseDouble(height)/100)*(Float.parseFloat(height)/100));
                bmiResult2 = (int)bmiResult1*100/100.0;

                textResult1.setText("1. " + bloodtype + "형 " + gender + "입니다.");
                textResult2 .setText("2. 신체 질량 지수는 " + bmiResult2 + "입니다." );
            }
        });




    }
}