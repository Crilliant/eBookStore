package com.example.ebookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookRecyclerViewAdapter.ItemClickListener{

    BookRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // classNum=0的所有数据
        ArrayList<Book> books = queryAllBooks(2);

        RecyclerView recyclerView = findViewById(R.id.BookListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookRecyclerViewAdapter(this, books);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    // 查询库中所有book，根据5个属性生成列表
    ArrayList<Book>  queryAllBooks (int classNum){
        DbOpenHelper helper = new DbOpenHelper(MainActivity.this);
        SQLiteDatabase db =helper.getReadableDatabase();
        ArrayList<Book>  books = new ArrayList<>();
        Cursor cursor = db.query("book", new String[]{"bookID","title","author","cover","price"},
                "classNum=?", new String[]{Integer.toString(classNum)},
                null,null,null);

        String title, author, cover;
        int id;
        float price;
        // 查询结果，移动光标
        while (cursor.moveToNext()){
            // 未找到列名就抛异常
            title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            id = cursor.getInt(cursor.getColumnIndexOrThrow("bookID"));
            cover = cursor.getString(cursor.getColumnIndexOrThrow("cover"));
            price = cursor.getFloat(cursor.getColumnIndexOrThrow("price"));
            books.add(new Book(id, title, author, cover, price));
            Log.i("cover:",cover);
        }
        db.close();
        cursor.close();
        return books;
    }

    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, BookDetail.class);
//        intent.putExtra("id", )
    }
}