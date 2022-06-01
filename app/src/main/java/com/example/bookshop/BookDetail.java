// 图书详情页面activity
// Created by cyx
package com.example.bookshop;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookDetail extends AppCompatActivity {

    ImageView coverView;
    TextView titleView, authorView, classView, priceView, detailView,idView, cntView;
    Button cartBtn, addBtn, subBtn;
    String bookID;
    String[] classDict = {"历史","哲学","编程","科幻"};
    int bookCnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // 获取图书id，显式传递
        Intent intent = getIntent();
        bookID = intent.getStringExtra("id");

//        Toast.makeText(this, bookID, Toast.LENGTH_SHORT).show();

        // 绑定View 、button组件
        bindView();
        // 查库，初始化View数据
        queryData();

        // 点击加号
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("click","before click ,bookcnt = "+bookCnt);
                bookCnt++;
                cntView.setText(bookCnt+"");
            }
        });
        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("click","before click ,bookcnt = "+bookCnt);
                if(bookCnt > 0){
                    bookCnt--;
                    cntView.setText(bookCnt+"");
                }
            }
        });

        // 点击加入购物车，插入cart table
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbOpenHelper helper = new DbOpenHelper(BookDetail.this);
                SQLiteDatabase db =helper.getWritableDatabase();
                String insert_sql="insert into cart values (" +
                        bookID + ", " + Integer.toString(bookCnt) + ")";
                db.execSQL(insert_sql);
                Toast.makeText(getApplicationContext(), "加入cart!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    // 组件绑定
    void bindView(){
        // 获取View
        coverView=(ImageView)findViewById(R.id.detail_cover);
        titleView=(TextView) findViewById(R.id.detail_title);
        authorView=(TextView) findViewById(R.id.detail_author);
        classView=(TextView) findViewById(R.id.detail_class);
        idView=(TextView) findViewById(R.id.detail_id);
        detailView=(TextView) findViewById(R.id.detail_info);
        priceView=(TextView) findViewById(R.id.bottom_price);
        cntView=(TextView)findViewById(R.id.book_cnt);

        // 获取button
        cartBtn=(Button) findViewById(R.id.cart_btn);
        addBtn=(Button) findViewById(R.id.add_btn);
        subBtn=(Button) findViewById(R.id.sub_btn);

    }
    void queryData(){
        DbOpenHelper helper = new DbOpenHelper(BookDetail.this);
        SQLiteDatabase db =helper.getReadableDatabase();
        Cursor cursor = db.query("book", new String[]{"title","author","cover","price","classNum","detail"},
                "bookID=?", new String[]{bookID},
                null,null,null);
        String title="乌克兰拖拉机简史", author="cyx", cover="love", detail="";
        float price=0;
        int classNum=0;
        // 查询结果，移动光标
        while (cursor.moveToNext()){
            // 未找到列名就抛异常
            title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            cover = cursor.getString(cursor.getColumnIndexOrThrow("cover"));
            price = cursor.getFloat(cursor.getColumnIndexOrThrow("price"));
            detail=cursor.getString(cursor.getColumnIndexOrThrow("detail"));
            classNum = cursor.getInt(cursor.getColumnIndexOrThrow("classNum"));
        }
        db.close();
        cursor.close();
        // 设置封面
        int coverId = getResources().getIdentifier(cover,"drawable","com.example.ebookstore");
        Drawable img = getDrawable(coverId);
        coverView.setImageDrawable(img);
        titleView.setText(title);
        authorView.setText(author);
        classView.setText(classDict[classNum]);
        idView.setText(bookID);
        detailView.setText(detail);
        priceView.setText(String.format("%.2f", price));
    }


}