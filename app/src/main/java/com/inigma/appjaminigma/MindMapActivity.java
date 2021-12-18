package com.inigma.appjaminigma;

import static com.inigma.appjaminigma.MainActivity.mindMapData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MindMapActivity extends AppCompatActivity {

    private InputMethodManager imm;
    View[] stick;
    TextView tv_title;
    Button btn_title;
    Button[] btn_option,btn_add;
    EditText[] et_option;
    String title;
    String[] option;
    int num;
    Boolean first = false, second = false, third = false, four = false;
    Boolean first_a = false, first_b = false,second_a = false, third_a = false, four_a = false,second_b = false, third_b = false, four_b = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindmap);
        init();
        initValue();
        setValue();
//        checkValue();
        addOption();
    }

    void addOption() {
        for (int i = 0; i < 12; i++) {
            int finalI = i;
            btn_add[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn_add[finalI].setVisibility(View.GONE);
                    btn_option[finalI].setVisibility(View.VISIBLE);
                    et_option[finalI].setVisibility(View.VISIBLE);
                    et_option[finalI].setFocusable(true);
                    et_option[finalI].requestFocus();
                    showKeyboard();

                }
            });
        et_option[i].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = et_option[finalI].getText().toString();
                btn_option[finalI].setText(value);
                et_option[finalI].setVisibility(View.GONE);
                if (value.length() >= 4) {
//                    setOptionValue(value);
                    InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    switch (finalI) {
                        case 0:
                            first = true;
                            break;
                        case 1:
                            second = true;
                            break;
                        case 2:
                            third = true;
                            break;
                        case 3:
                            four = true;
                            break;
                        case 4:
                            first_a = true;
                            break;
                        case 5:
                            first_b = true;
                            break;
                        case 6:
                            second_a = true;
                            break;
                        case 7:
                            second_b = true;
                            break;
                        case 8:
                            third_a = true;
                            break;
                        case 9:
                            third_b = true;
                            break;
                        case 10:
                            four_a = true;
                            break;
                        case 11:
                            four_b = true;
                            break;
                    }
                    setVisible();
                }
            }
        });
        }
    }

    void setVisible() {
        if (first == true) {
            if (first_a == true) {
                stick[1].setVisibility(View.VISIBLE);
                btn_add[5].setVisibility(View.VISIBLE);
            } else if (first_b == true) {
                stick[0].setVisibility(View.VISIBLE);
                btn_add[4].setVisibility(View.VISIBLE);
            } else {
                stick[0].setVisibility(View.VISIBLE);
                stick[1].setVisibility(View.VISIBLE);
                btn_add[4].setVisibility(View.VISIBLE);
                btn_add[5].setVisibility(View.VISIBLE);
            }
        }

        if (second == true) {
            if (second_a == true) {
                stick[3].setVisibility(View.VISIBLE);
                btn_add[7].setVisibility(View.VISIBLE);
            } else if (second_b == true) {
                stick[2].setVisibility(View.VISIBLE);
                btn_add[6].setVisibility(View.VISIBLE);
            } else {
                stick[2].setVisibility(View.VISIBLE);
                stick[3].setVisibility(View.VISIBLE);
                btn_add[6].setVisibility(View.VISIBLE);
                btn_add[7].setVisibility(View.VISIBLE);
            }
        }

        if (third == true) {
            if (third_a == true) {
                stick[5].setVisibility(View.VISIBLE);
                btn_add[9].setVisibility(View.VISIBLE);
            } else if (third_b == true) {
                stick[4].setVisibility(View.VISIBLE);
                btn_add[8].setVisibility(View.VISIBLE);
            } else {
                stick[4].setVisibility(View.VISIBLE);
                stick[5].setVisibility(View.VISIBLE);
                btn_add[8].setVisibility(View.VISIBLE);
                btn_add[9].setVisibility(View.VISIBLE);
            }
        }

        if (four == true) {
            if (four_a == true) {
                stick[7].setVisibility(View.VISIBLE);
                btn_add[11].setVisibility(View.VISIBLE);
            } else if (four_b == true) {
                stick[6].setVisibility(View.VISIBLE);
                btn_add[10].setVisibility(View.VISIBLE);
            } else {
                stick[6].setVisibility(View.VISIBLE);
                stick[7].setVisibility(View.VISIBLE);
                btn_add[10].setVisibility(View.VISIBLE);
                btn_add[11].setVisibility(View.VISIBLE);
            }
        }
    }

    void setOptionValue(String value) {
        mindMapData[num].setOption(value);
    }

    void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    void setValue() {
        tv_title.setText(title);
        btn_title.setText(title);
        for (int i = 0; i < 12; i++) {
            if (option[i] != null) {
                btn_option[i].setText(option[i]);
            }
        }
    }

    void initValue() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        num = intent.getIntExtra("num", 0);
        try {
            for (int i = 0; i < 12; i++) {
                option[i] = mindMapData[num].getOption();
            }

        } catch (Exception e) {}

    }

    void checkValue() {
        for (int i = 0; i < 12; i++) {
            String value;
            value = btn_option[i].getText().toString();
            if (value != null) {
                Log.d("test", "no");
                btn_option[i].setVisibility(View.GONE);
                et_option[i].setVisibility(View.GONE);

                btn_add[i].setVisibility(View.VISIBLE);
            }
        }
    }

    void init() {
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        tv_title = (TextView) findViewById(R.id.TV_title);
        btn_title = (Button) findViewById(R.id.btn_title);
        option = new String[12];
        btn_option = new Button[12];
        btn_option[0] = (Button) findViewById(R.id.btn_option1);
        btn_option[1] = (Button) findViewById(R.id.btn_option2);
        btn_option[2] = (Button) findViewById(R.id.btn_option3);
        btn_option[3] = (Button) findViewById(R.id.btn_option4);
        btn_option[4] = (Button) findViewById(R.id.btn_option5);
        btn_option[5] = (Button) findViewById(R.id.btn_option6);
        btn_option[6] = (Button) findViewById(R.id.btn_option7);
        btn_option[7] = (Button) findViewById(R.id.btn_option8);
        btn_option[8] = (Button) findViewById(R.id.btn_option9);
        btn_option[9] = (Button) findViewById(R.id.btn_option10);
        btn_option[10] = (Button) findViewById(R.id.btn_option11);
        btn_option[11] = (Button) findViewById(R.id.btn_option12);


        et_option = new EditText[12];
        et_option[0] = (EditText) findViewById(R.id.ET_option1);
        et_option[1] = (EditText) findViewById(R.id.ET_option2);
        et_option[2] = (EditText) findViewById(R.id.ET_option3);
        et_option[3] = (EditText) findViewById(R.id.ET_option4);
        et_option[4] = (EditText) findViewById(R.id.ET_option5);
        et_option[5] = (EditText) findViewById(R.id.ET_option6);
        et_option[6] = (EditText) findViewById(R.id.ET_option7);
        et_option[7] = (EditText) findViewById(R.id.ET_option8);
        et_option[8] = (EditText) findViewById(R.id.ET_option9);
        et_option[9] = (EditText) findViewById(R.id.ET_option10);
        et_option[10] = (EditText) findViewById(R.id.ET_option11);
        et_option[11] = (EditText) findViewById(R.id.ET_option12);

        btn_add = new Button[12];
        btn_add[0] = (Button) findViewById(R.id.btn_add_opt1);
        btn_add[1] = (Button) findViewById(R.id.btn_add_opt2);
        btn_add[2] = (Button) findViewById(R.id.btn_add_opt3);
        btn_add[3] = (Button) findViewById(R.id.btn_add_opt4);
        btn_add[4] = (Button) findViewById(R.id.btn_add_opt5);
        btn_add[5] = (Button) findViewById(R.id.btn_add_opt6);
        btn_add[6] = (Button) findViewById(R.id.btn_add_opt7);
        btn_add[7] = (Button) findViewById(R.id.btn_add_opt8);
        btn_add[8] = (Button) findViewById(R.id.btn_add_opt9);
        btn_add[9] = (Button) findViewById(R.id.btn_add_opt10);
        btn_add[10] = (Button) findViewById(R.id.btn_add_opt11);
        btn_add[11] = (Button) findViewById(R.id.btn_add_opt12);

        stick = new View[8];
        stick[0] = (View) findViewById(R.id.view_first_a);
        stick[1] = (View) findViewById(R.id.view_first_b);
        stick[2] = (View) findViewById(R.id.view_second_a);
        stick[3] = (View) findViewById(R.id.view_second_b);
        stick[4] = (View) findViewById(R.id.view_third_a);
        stick[5] = (View) findViewById(R.id.view_third_b);
        stick[6] = (View) findViewById(R.id.view_four_a);
        stick[7] = (View) findViewById(R.id.view_four_b);

        for (int i = 0; i < 12; i++) {
            btn_option[i].setText(null);
            btn_option[i].setVisibility(View.GONE);
            et_option[i].setVisibility(View.GONE);
        }
        for (int i = 4; i <12; i++) {
            btn_add[i].setVisibility(View.GONE);
        }

        for (int i = 0; i < 8; i++) {
            stick[i].setVisibility(View.INVISIBLE);
        }

    }
}