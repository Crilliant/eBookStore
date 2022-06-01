package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        ListView listView= (ListView)findViewById(R.id.listview);
        List<HistoryBean> list=new ArrayList<>();
        init(list);
        HistoryAdapter historyAdapter=new HistoryAdapter
                (HistoryDetailActivity.this,R.layout.list,list);
        listView.setAdapter(historyAdapter);
    }

    private void init(List<HistoryBean> list)
    {
        HistoryBean bean1=new HistoryBean("玩具","1","2022-5-14"
                ,1,30,R.drawable.wanju);
        HistoryBean bean2=new HistoryBean("香水","2","2022-5-23"
                ,1,30,R.drawable.xiangshui);
        list.add(bean1);
        list.add(bean2);
    }
}