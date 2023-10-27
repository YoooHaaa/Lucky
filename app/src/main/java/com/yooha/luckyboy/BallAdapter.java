package com.yooha.luckyboy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class BallAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<Data> datas;

    public BallAdapter(){

    }
    public BallAdapter(Context context, ArrayList<Data> datas){
        this.context=context;
        this.datas=datas;
    }

    public void add(Data data){
        this.datas.add(data);
    }

    public void update(Data data){
        int len = this.datas.size();
        this.datas.set(len - 1, data);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_lucky, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_ball_1 = (TextView) view.findViewById(R.id.tv_1 );
            viewHolder.tv_ball_2 = (TextView) view.findViewById(R.id.tv_2 );
            viewHolder.tv_ball_3 = (TextView) view.findViewById(R.id.tv_3 );
            viewHolder.tv_ball_4 = (TextView) view.findViewById(R.id.tv_4 );
            viewHolder.tv_ball_5 = (TextView) view.findViewById(R.id.tv_5 );
            viewHolder.tv_ball_6 = (TextView) view.findViewById(R.id.tv_6 );
            viewHolder.tv_ball_7 = (TextView) view.findViewById(R.id.tv_7 );
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Log.i("lucky", "getView");
        viewHolder.tv_ball_1.setText(datas.get(i).ball_1);
        viewHolder.tv_ball_2.setText(datas.get(i).ball_2);
        viewHolder.tv_ball_3.setText(datas.get(i).ball_3);
        viewHolder.tv_ball_4.setText(datas.get(i).ball_4);
        viewHolder.tv_ball_5.setText(datas.get(i).ball_5);
        viewHolder.tv_ball_6.setText(datas.get(i).ball_6);
        viewHolder.tv_ball_7.setText(datas.get(i).ball_7);
        return view;
    }
}
