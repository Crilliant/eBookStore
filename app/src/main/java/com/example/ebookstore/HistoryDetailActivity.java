/*
 * Created by cyx on 2022.5.22
 *
 *  */
package com.example.ebookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        Intent intent = getIntent();
        int bookImg = intent.getIntExtra("bookImg", 1);
        int count = intent.getIntExtra("count", 1);
        String name = intent.getStringExtra("name");
        String time = intent.getStringExtra("time");
        int shoppingID = intent.getIntExtra("shoppingID", 1);

        ID = findViewById(R.id.ID);
        date = findViewById(R.id.time);
        total = findViewById(R.id.total);
        img = findViewById(R.id.image);
        bookName = findViewById(R.id.bookName);
        quantity = findViewById(R.id.quantity);

        ID.setText("订单编号:" + shoppingID);
        date.setText("订单完成时间:" + time);
        total.setText("总价格：23.3");
        bookName.setText("书名:\n" + name);
        quantity.setText("数量:" + count);
        img.setImageResource(bookImg);


    }
}