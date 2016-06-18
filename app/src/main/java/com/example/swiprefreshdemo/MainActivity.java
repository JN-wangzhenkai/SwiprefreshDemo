package com.example.swiprefreshdemo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;
    private List<String> list;
    private myAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip);

        ListView lv= (ListView) findViewById(R.id.listview);
        adapter = new myAdapter();

        lv.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this);

        list = new ArrayList<String>();
        for (int i = 0; i < 24; i++)
        {
            list.add("北京" + i);
        }
    }


    @Override
    public void onRefresh() {

        refreshLayout.setRefreshing(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                list.add(0,"hahahahahhah");

                adapter.notifyDataSetChanged();

                refreshLayout.setRefreshing(false);

            }
        },1000);
    }


    public class myAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if(list!=null){
            return list.size();}
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if(list!=null){
            return list.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {

            return position;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null ){
             convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item,null);
            }
                TextView textView= (TextView) convertView.findViewById(R.id.textview);

                textView.setText(list.get(position));


            return convertView;
        }
    }



}
