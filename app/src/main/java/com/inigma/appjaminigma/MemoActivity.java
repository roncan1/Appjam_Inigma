package com.inigma.appjaminigma;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemoActivity extends AppCompatActivity {

    TextView titleText; //주제 textView
    EditText customText; //설명 editText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo); //출

        //텍스트뷰에 텍스트가 출력되게끔하기
        titleText = (TextView)findViewById(R.id.showText);
        customText = (EditText)findViewById(R.id.makeText);
    }
}