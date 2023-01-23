package com.cookandroid.hw02_bmicalculation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etHeight, etWeight;
    RadioGroup rGroup;
    RadioButton rbtnFemale, rbtnMale, rbtnChecked;
    Spinner spinnerBlood;
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
        btnResult = findViewById(R.id.btnResult);
        textResult1 = findViewById(R.id.textResult1);
        textResult2 = findViewById(R.id.textResult2);

        // 혈액형 스피너 선택
        final String[] blood = {"A", "B", "O", "AB"};
        spinnerBlood = findViewById(R.id.spinnerBlood);
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, blood);
        spinnerBlood.setAdapter(adapter);

        // 몸 상태 측정 버튼을 누르면
        // 1. 혈액형과 성별 출력 예) A형 여자입니다.
        // 2. 신체 질량 지수 출력 예) 신체 질량 지수는 21.4입니다.
        // 체크된 버튼 종류에 따라 이미지가 표시되거나 표시되지 않는다.

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    bloodtype = spinnerBlood.getSelectedItem().toString();
                    rbtnChecked = (RadioButton) findViewById(rGroup.getCheckedRadioButtonId());
                    gender = rbtnChecked.getText().toString();
                    textResult1.setText("1. " + bloodtype + "형 " + gender + "입니다.");

                    height = etHeight.getText().toString();
                    weight = etWeight.getText().toString();
                // 키와 체중이 공란이면 대화상자 띄우기
                    if (height.equals("") || weight.equals("")) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    dlg.setTitle("키와 체중");
                    dlg.setView(getLayoutInflater().inflate(R.layout.custom_dialog, null));
                    dlg.show();
                    } else {
                    bmiResult1 = Double.parseDouble(weight) / ((Double.parseDouble(height) / 100) * (Float.parseFloat(height) / 100));
                    bmiResult2 = (int) bmiResult1 * 100 / 100.0;
                    textResult2.setText("2. 신체 질량 지수는 " + bmiResult2 + "입니다.");
                    }
                // 습관에 따라 그림 표시하기, 갤러리 사용
                Gallery gal = (Gallery) findViewById(R.id.gal);
                ArrayList<Integer> alHabit = new ArrayList<Integer>();
                CheckBox btnDrinking = findViewById(R.id.btnDrinking);
                CheckBox btnSmoking = findViewById(R.id.btnSmoking);
                CheckBox btnWorkout = findViewById(R.id.btnWorkout);
                if (btnDrinking.isChecked()){
                    alHabit.add(R.drawable.drinking);
                }
                if (btnSmoking.isChecked()){
                    alHabit.add(R.drawable.ciga);
                }
                if (!btnWorkout.isChecked()){
                    alHabit.add(R.drawable.running);
                }
                if (btnDrinking.isChecked() || btnSmoking.isChecked() || !btnWorkout.isChecked()){
                    gal.setAdapter(new ImageAdapter(MainActivity.this, alHabit));
                } else {
                    gal.removeAllViewsInLayout();
                }

            }
        });

    }

    public class ImageAdapter extends BaseAdapter {

        Context context;
        // 버튼 체크와 갤러리 이미지 표시 연결이 안 됨..
        ArrayList<Integer> alHabit = null;

        public ImageAdapter(Context c, ArrayList<Integer> alHabit) {
            context = c;
            this.alHabit = alHabit;
        }

        @Override
        public int getCount() {
            return alHabit.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(500, 500));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(alHabit.get(position));

            return imageview;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


    }
}