package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryDetailActivity extends AppCompatActivity {
    TextView ID;//订单编号
    TextView date;//订单时间
    TextView total;//总价
    ImageView img;//图书图片
    TextView bookName;//书名
    TextView quantity;//购买数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        Intent intent=getIntent();
        int bookImg=intent.getIntExtra("bookImg",1);
        int count=intent.getIntExtra("count",1);
        String name=intent.getStringExtra("name");
        String time=intent.getStringExtra("time");
        int shoppingID=intent.getIntExtra("shoppingID",1);
        
        ID=findViewById(R.id.ID);
        date=findViewById(R.id.time);
        total=findViewById(R.id.total);
        img=findViewById(R.id.image);
        bookName=findViewById(R.id.bookName);
        quantity=findViewById(R.id.quantity);

        ID.setText("订单编号:"+shoppingID);
        date.setText("订单完成时间:"+time);
        total.setText("总价格：23.3");
        bookName.setText("书名:\n"+name);
        quantity.setText("数量:"+count);
        img.setImageResource(bookImg);


//        ListView listView= (ListView)findViewById(R.id.listview);
//        List<HistoryBean> list=new ArrayList<>();
//        init(list);
//        HistoryDetailAdapter historyAdapter=new HistoryDetailAdapter
//                (HistoryDetailActivity.this,R.layout.detail,list);
//        listView.setAdapter(historyAdapter);
    }

//    private void init(List<HistoryBean> list)
//    {
//        HistoryBean bean1=new HistoryBean("玩具","1","2022-5-14"
//                ,1,30,R.drawable.wanju);
//        HistoryBean bean2=new HistoryBean("香水","2","2022-5-23"
//                ,1,30,R.drawable.xiangshui);
//        list.add(bean1);
//        list.add(bean2);
//    }
}