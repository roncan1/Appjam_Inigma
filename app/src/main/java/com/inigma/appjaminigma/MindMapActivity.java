package com.inigma.appjaminigma;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MindMapActivity extends AppCompatActivity {

    TextView tv_title;
    Button btn_title;
    String title;
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindmap);
        init();
        initValue();
        setValue();
    }

    void setValue() {
        tv_title.setText(title);
        btn_title.setText(title);

    }

    void initValue() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        num = intent.getIntExtra("num", 0);
    }

    void init() {
        tv_title = (TextView) findViewById(R.id.TV_title);
        btn_title = (Button) findViewById(R.id.btn_title);
    }
}