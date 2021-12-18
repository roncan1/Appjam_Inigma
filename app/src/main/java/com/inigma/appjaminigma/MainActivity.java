package com.inigma.appjaminigma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // 리스트를 감싸고 있는 레이아웃
    LinearLayout memoLayout, mindMapLayout;
    // 리스트 프레임 전환 레이아웃
    Button btnMemo, btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); // 초기값 세팅
        setMemoDataList(); // 메모 리스트 세팅
        setMindMapDataList(); // 마인드맵 리스트 세팅
        changeList(); // 프레임 전환
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void changeList() {
        btnMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memoLayout.setVisibility(View.VISIBLE);
                mindMapLayout.setVisibility(View.GONE);
            }
        });
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memoLayout.setVisibility(View.GONE);
                mindMapLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    void setMemoDataList() {
        final String[] DATA_LIST = {"data1", "data2", "data3"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, DATA_LIST);

        ListView listView = (ListView) findViewById(R.id.MemoList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                String value = (String) adapter.getItem(i);
                intent.putExtra("data", value);
                startActivity(intent);

            }
        });
    }

    void setMindMapDataList() {
        final String[] DATA_LIST = {"data4", "data5", "data6"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, DATA_LIST);

        ListView listView = (ListView) findViewById(R.id.mindMapList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MindMapActivity.class);
                String value = (String) adapter.getItem(i);
                intent.putExtra("data", value);
                startActivity(intent);

            }
        });
    }

    void init() {
        memoLayout = (LinearLayout) findViewById(R.id.frameMemo);
        mindMapLayout = (LinearLayout) findViewById(R.id.frameMain);
        btnMemo = (Button) findViewById(R.id.btnMemo);
        btnMain = (Button) findViewById(R.id.btnMainMap);
    }

}