// 图书详情页面activity
package com.example.ebookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetail extends AppCompatActivity {

    ImageView coverView;
    TextView titleView, authorView, classView, priceView, detailView,idView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // 获取图书id，显式传递
        Intent intent = getIntent();
        String bookID = intent.getStringExtra("id");
        Toast.makeText(this, bookID, Toast.LENGTH_SHORT).show();

        // 获取View
        coverView=(ImageView)findViewById(R.id.detail_cover);
        titleView=(TextView) findViewById(R.id.detail_title);
        authorView=(TextView) findViewById(R.id.detail_author);
        classView=(TextView) findViewById(R.id.detail_class);
        idView=(TextView) findViewById(R.id.detail_id);
        detailView=(TextView) findViewById(R.id.detail_info);

    }
}