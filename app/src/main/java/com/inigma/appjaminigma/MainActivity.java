package com.inigma.appjaminigma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static DataModel[] mindMapData, memoData;

    // 리스트를 감싸고 있는 레이아웃
    LinearLayout memoLayout, mindMapLayout;
    // 리스트 프레임 전환 레이아웃
    Button btnMemo, btnMain;
    // 커스텀 다이얼로그
    Dialog dialog;

    EditText etTitle, etExplain;
    CheckBox checkMemo, checkMind;

    ListView mindMapList, memoList;
    ListItemAdapter memoAdapter, mindAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); // 초기값 세팅
        setMemoDataList(); // 메모 리스트 세팅
        setMindMapDataList(); // 마인드맵 리스트 세팅
        changeList(); // 프레임 전환
        setDialog(); // 다이얼로그 세팅
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void changeList() {

        btnMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mindMapLayout.setVisibility(View.GONE);

                overridePendingTransition(R.anim.role_right_enter, R.anim.role_right_exit); //이동 구현하려했지만 실패

                //view.clearAnimation();
                Animation animOutW = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pade_out_views);
                mindMapList.startAnimation(animOutW);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        memoLayout.setVisibility(View.VISIBLE);
                        Animation animInW = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pade_in_views);
                        memoList.startAnimation(animInW);
                    }
                }, 150);

            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memoLayout.setVisibility(View.GONE);

                overridePendingTransition(R.anim.role_left_enter, R.anim.role_left_exit); //이동 구현하려했지만 실패


                //view.clearAnimation();
                Animation animOutW = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pade_out_views);
                memoList.startAnimation(animOutW);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mindMapLayout.setVisibility(View.VISIBLE);
                        Animation animInW = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pade_in_views);
                        mindMapList.startAnimation(animInW);
                    }
                }, 150);
            }
        });
    }

    void setMemoDataList() {
        memoList = (ListView) findViewById(R.id.MemoList);

        memoAdapter = new ListItemAdapter();

        memoList.setAdapter(memoAdapter);

        memoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view.findViewById(R.id.TV_title);
                String title = tv.getText().toString();
                Intent intent = new Intent(MainActivity.this, MemoActivity.class);
                intent.putExtra("title", String.valueOf(title));
                intent.putExtra("num", i);
                startActivity(intent);
            }
        });
    }

    void setMindMapDataList() {
        mindMapList = (ListView) findViewById(R.id.mindMapList);

        mindAdapter = new ListItemAdapter();

        mindMapList.setAdapter(mindAdapter);

        mindMapList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = (TextView) view.findViewById(R.id.TV_title);
                String title = tv.getText().toString();
                Intent intent = new Intent(MainActivity.this, MindMapActivity.class);
                intent.putExtra("title", String.valueOf(title));
                intent.putExtra("num", i);
                startActivity(intent);
            }
        });
    }

    void setDialog() {
        dialog = new Dialog(MainActivity.this);       // Dialog 초기화
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialog.setContentView(R.layout.dialog_add_data);             // xml 레이아웃 파일과 연결

        // 버튼: 커스텀 다이얼로그 띄우기
        findViewById(R.id.btnPopupDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustumDialog(); // 아래 showDialog01() 함수 호출
            }
        });
    }

    public void showCustumDialog() {
        dialog.show(); // 다이얼로그 띄우기
        checkMemo = (CheckBox) dialog.findViewById(R.id.checkMemo);
        checkMind = (CheckBox) dialog.findViewById(R.id.checkMind);
        etTitle = (EditText) dialog.findViewById(R.id.editTitle);
        etExplain = (EditText) dialog.findViewById(R.id.editExplain);

        // 취소 버튼
        Button btnCancel = dialog.findViewById(R.id.btnDialogCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });

        // 확인 버튼
        Button btnConfirm = dialog.findViewById(R.id.btnDialogConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkMemo.isChecked()) {
                    memoAdapter.addItem(new DataModel(etTitle.getText().toString(), etExplain.getText().toString()));
                    memoData[memoAdapter.getCount()] = new DataModel(etTitle.getText().toString(), etExplain.getText().toString());
                } else if (checkMind.isChecked()) {
                    mindAdapter.addItem(new DataModel(etTitle.getText().toString(), etExplain.getText().toString()));
                    mindMapData[mindAdapter.getCount()] = new DataModel(etTitle.getText().toString(), etExplain.getText().toString());
                }
                memoList.setAdapter(memoAdapter);
                mindMapList.setAdapter(mindAdapter);
                dialog.dismiss();
            }
        });
    }

    void init() {
        memoLayout = (LinearLayout) findViewById(R.id.frameMemo);
        mindMapLayout = (LinearLayout) findViewById(R.id.frameMain);
        btnMemo = (Button) findViewById(R.id.btnMemo);
        btnMain = (Button) findViewById(R.id.btnMainMap);
        mindMapData = new DataModel[12];
        memoData = new DataModel[12];

    }

}