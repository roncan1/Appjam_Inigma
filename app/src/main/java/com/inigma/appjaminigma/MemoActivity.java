package com.inigma.appjaminigma;

import static com.inigma.appjaminigma.MainActivity.memoData;
import static com.inigma.appjaminigma.MainActivity.mindMapData;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemoActivity extends AppCompatActivity {

    TextView tv1, tv2;
    EditText et1;
    String title;
    int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo); //출
        init();
        initValue();
    }

    void initValue() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        num = intent.getIntExtra("num", 0);
        tv1.setText(title);
        tv2.setText(title);
        try {
            et1.setText(memoData[num].getMemo());

        } catch (Exception e) {
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            String value = et1.getText().toString();
            memoData[num].setMemo(value);

        } catch (Exception e) {

        }
    }

    void init() {
        tv1 = (TextView) findViewById(R.id.TV_title1);
        tv2 = (TextView) findViewById(R.id.title);
        et1 = (EditText) findViewById(R.id.et_memo);
    }
}
