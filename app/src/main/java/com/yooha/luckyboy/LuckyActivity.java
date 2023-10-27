package com.yooha.luckyboy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Random;


public class LuckyActivity extends Activity {
    private BallAdapter balls;
    private ArrayList<Data> datas;
    private ListView listView;
    private int time;
    private int lucky;
    private int playing;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);
        this.datas = new ArrayList<Data>();
        this.balls = new BallAdapter(this, datas);
        init_listview();

        Intent intent = getIntent();
        time = intent.getIntExtra("time", 0);
        lucky = intent.getIntExtra("lucky", 0);
        playing = intent.getIntExtra("playing", 0);
        roll();
    }

    public void init_listview(){
        listView = findViewById(R.id.lv_show);
        listView.setAdapter(balls);
    }

    public void roll(){
        if (playing == 1) {
            roll_big_happy(time);
        } else if (playing == 2) {
            roll_bouble_ball(time);
        } else {
            finish();
        }
    }

    public void set_ball(int[] balls){
        Data data = new Data();
        data.ball_1 = String.valueOf(balls[0]);
        data.ball_2 = String.valueOf(balls[1]);
        data.ball_3 = String.valueOf(balls[2]);
        data.ball_4 = String.valueOf(balls[3]);
        data.ball_5 = String.valueOf(balls[4]);
        data.ball_6 = String.valueOf(balls[5]);
        data.ball_7 = String.valueOf(balls[6]);
        this.balls.add(data);
    }

    public void insertSort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = tmp;
        }
    }

    public int[] convert_array(ArrayList<Integer> raw) {
        int[] ret = new int[raw.size()];
        for (int i = 0; i < raw.size(); i++) {
            ret[i] = raw.get(i);

        }
        return ret;
    }

    public int[] get_remaining_ball(int[] all, ArrayList get) { //获取剩余可选的球
        if (get.size() == 0) {
            return all;
        }

        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < all.length; i++) {
            boolean flag = false;
            for (int j = 0; j < get.size(); j++) {
                if (all[i] == (int) get.get(j)) {
                    flag = true;
                    continue;
                }
            }
            if (!flag){
                tmp.add(all[i]);
            }
        }
        return convert_array(tmp);
    }

    public void sort_big_happy_ball(int[] balls) {
        int[] left = new int[5];
        for (int i = 0; i < 5; i++) {
            left[i] = balls[i];
        }
        insertSort(left);

        int[] right = new int[2];
        for (int i = 0; i < 2; i++) {
            right[i] = balls[i + 5];
        }
        insertSort(right);

        for (int i = 0; i < 5; i++) {
            balls[i] = left[i];
        }
        for (int i = 0; i < 2; i++) {
            balls[i + 5] = right[i];
        }
    }

    public void roll_once_big_happy() {
        int[] left = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
        int[] right = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ArrayList<Integer> ballArray = new ArrayList();
        ArrayList<Integer> ballArrayRight = new ArrayList();

        for (int i = 0; i < 5; i++) {
            if ((this.lucky + random.nextInt(35 - i)) % 2 == 0){
                int[] all = get_remaining_ball(left, ballArray);
                ballArray.add(all[(this.lucky + random.nextInt(35 - i)) % (35 - i)]);
            }else{
                ballArray.add(LuckyUtil.get_lucky_number(get_remaining_ball(left, ballArray)));
            }
        }

        for (int i = 0; i < 2; i++) {
            if ((this.lucky + random.nextInt(12 - i)) % 2 == 0){
                int[] all = get_remaining_ball(right, ballArrayRight);
                ballArrayRight.add(all[(this.lucky + random.nextInt(12 - i)) % (12 - i)]);
            }else{
                ballArrayRight.add(LuckyUtil.get_lucky_number(get_remaining_ball(right, ballArrayRight)));
            }
            ballArray.add(ballArrayRight.get(i));
        }
        int[] tmp = convert_array(ballArray);
        sort_big_happy_ball(tmp);
        set_ball(tmp);
    }


    public void roll_big_happy(int value) {
        for (int i = 0; i < value; i++) {
            roll_once_big_happy();
        }
    }

    //******************************************************************************************
    public void sort_bouble_ball_ball(int[] balls) {
        int[] left = new int[6];
        for (int i = 0; i < 6; i++) {
            left[i] = balls[i];
        }
        insertSort(left);

        for (int i = 0; i < 6; i++) {
            balls[i] = left[i];
        }
    }

    public void roll_once_bouble_ball() {
        int[] left = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33};
        int[] right = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        ArrayList<Integer> ballArray = new ArrayList();

        for (int i = 0; i < 6; i++) {
            if ((this.lucky + random.nextInt(33 - i)) % 2 == 0){
                int[] all = get_remaining_ball(left, ballArray);
                ballArray.add(all[(this.lucky + random.nextInt(33 - i)) % (33 - i)]);
            }else{
                ballArray.add(LuckyUtil.get_lucky_number(get_remaining_ball(left, ballArray)));
            }
        }

        if ((this.lucky + random.nextInt(16)) % 2 == 0){
            ballArray.add(right[(this.lucky + random.nextInt(16)) % 16]);
        }else{
            ballArray.add(LuckyUtil.get_lucky_number(right));
        }

        int[] tmp = convert_array(ballArray);
        sort_bouble_ball_ball(tmp);
        set_ball(tmp);
    }

    public void roll_bouble_ball(int value) {
        for (int i = 0; i < value; i++) {
            roll_once_bouble_ball();
        }
    }
}


// 35 + 12
// 36 + 16

