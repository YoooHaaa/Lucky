package com.yooha.luckyboy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button m_bt_confirm;
    private TextView tv_input;
    private TextView tv_lucky;

    private RadioGroup radioGroup;

    private int select = 0; // 1-big happy 2-double balls


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_textview();
        init_button();
        init_radioGroup();

    }

    public void init_textview() {
        tv_input = findViewById(R.id.tv_number);
        tv_input.setInputType(InputType.TYPE_CLASS_NUMBER);
        tv_lucky = findViewById(R.id.tv_lucky);
        tv_lucky.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public void init_button() {
        m_bt_confirm = findViewById(R.id.bt_lucky);
        m_bt_confirm.setOnClickListener(this);
    }

    public void init_radioGroup() {
        this.radioGroup = findViewById(R.id.rg);
        this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rb_big_happy:
                        select = 1;
                        Toast.makeText(MainActivity.this, "you select Super Lotto", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_double_balls:
                        select = 2;
                        Toast.makeText(MainActivity.this, "you select Dual-colored Ball", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
                case R.id.bt_lucky: {
                    if (select == 0){
                        Toast.makeText(MainActivity.this, "please select playing method!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    String strInput = tv_input.getText().toString();
                    if (strInput.equals("")){
                        Toast.makeText(MainActivity.this, "How many of time do you want？", Toast.LENGTH_LONG).show();
                        return;
                    }
                    int input;
                    try{
                        input = Integer.parseInt(strInput);
                    }catch( Throwable th){
                        Toast.makeText(MainActivity.this, "How many of time do you want？", Toast.LENGTH_LONG).show();
                        return;
                    }

                    String lucky = tv_lucky.getText().toString();
                    Log.i("testluck", "lucky -> " + lucky);
                    int inputLucky;
                    try{
                        if (lucky.equals("")){
                            inputLucky = 0;
                        }else{
                            inputLucky = Integer.parseInt(lucky);
                        }
                    }catch( Throwable th){
                        Toast.makeText(MainActivity.this, "Please enter the number!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Intent intent = new Intent(MainActivity.this, LuckyActivity.class);
                    intent.putExtra("time", input);
                    intent.putExtra("lucky", inputLucky);
                    intent.putExtra("playing", select);
                    startActivity(intent);
                }
            }
        }
    }






