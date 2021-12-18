package com.inigma.appjaminigma;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MindMapActivity extends AppCompatActivity {

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindmap);
        initValue();
    }

    void initValue() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");

    }

}