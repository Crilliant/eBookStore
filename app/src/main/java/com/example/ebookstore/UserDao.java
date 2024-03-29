/*
 * Created by cyx on 2022.5.22
 *
 *  */
package com.example.ebookstore;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    DbOpenHelper dbOpenHelper;
    Context context;

    public UserDao(Context context)
    {
        dbOpenHelper =new DbOpenHelper(context);
        this.context=context;
    }

    public void insert(String account,String pwd,String table)//插入
    {
        SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("account",account);
        values.put("pwd",pwd);
        db.insert(table,null,values);
        db.close();
    }

    public void newHistory(String account)
    {
        SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
        db.execSQL("create table "+account+"History (id integer primary key autoincrement, bookName" +
                " vatchar(20), bookValue float, imageID integer, time varchar(20))");
    }

    public boolean userExist(String name)//判断用户是否存在
    {
        SQLiteDatabase db= dbOpenHelper.getReadableDatabase();
        Cursor cursor=db.query("user",null,"account=?",new String[]{name},
                null,null,null);
        if(cursor.getCount()!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean ifEmpty(String table)//判断是否表空
    {
        Cursor cursor= dbOpenHelper.getReadableDatabase().rawQuery("select * from"+table,null);
        if(cursor.getCount()==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean ifLogin(String name,String pwd)//判断是否登录成功
    {
        SQLiteDatabase db= dbOpenHelper.getReadableDatabase();
        Cursor cursor=db.query("user",null,"account=?",new String[]{name},
                null,null,null);
        if(cursor.getCount()==0)
        {
            return false;
        }
        else //cursor决定行，默认在-1，所以要移动，然后可以根据键值对得到要取的列
        {
            cursor.moveToFirst();
            int column=cursor.getColumnIndex("pwd");
            if(column>=0)
            {
                String pwdStore=cursor.getString(column);
                if(pwd.equals(pwdStore))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public List<Pair<Integer, Integer>> getCart()
    {
        //查询全部数据
        Cursor cursor = dbOpenHelper.getWritableDatabase()
                .query("cart",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        if(cursor.getCount() > 0)
        {
            //移动到首位
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                int bookId = cursor.getInt(0);
                int count = cursor.getInt(1);

                Pair<Integer, Integer> pair = Pair.create(bookId, count);

                list.add(pair);
                //移动到下一位
                cursor.moveToNext();
            }
        }

        cursor.close();
        dbOpenHelper.getWritableDatabase().close();

        return list;
    }

    public Book queryBookId (int bookId){
        SQLiteDatabase db =dbOpenHelper.getReadableDatabase();
        Book book = null;
        Cursor cursor = db.query("book", new String[]{"classNum","title","author","cover","price"},
                "bookID=?", new String[]{Integer.toString(bookId)},
                null,null,null);

        String title, author, cover;
        int id;
        float price;
        // 查询结果，移动光标
        while (true){
            if (!cursor.moveToNext()) break;
            // 未找到列名就抛异常
            title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            id = cursor.getInt(cursor.getColumnIndexOrThrow("classNum"));
            cover = cursor.getString(cursor.getColumnIndexOrThrow("cover"));
            price = cursor.getFloat(cursor.getColumnIndexOrThrow("price"));
            book = new Book(id, title, author, cover, price);
            Log.i("cover:",cover);
            break;
        }
        db.close();
        cursor.close();
        return book;
    }

    public void addOrder(String date,String table, int bookID,int count)
    {
        SQLiteDatabase db= dbOpenHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("dealTime",date);
        values.put("bookID",bookID);
        values.put("count",count);
        db.insert(table,null,values);
        db.close();
    }

    public void getOrder(List<HistoryBean> list)
    {
        SQLiteDatabase db= dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("orders",null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            @SuppressLint
                    ("Range") Cursor book=db.query
                    ("book",null,"bookID=?",
                            new String[]{cursor.getString(cursor.getColumnIndex("bookID"))},
                            null,null,null);
            book.moveToFirst();
            @SuppressLint("Range") HistoryBean historyBean=new HistoryBean(book.getString(book.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("orderID")),
                    cursor.getString(cursor.getColumnIndex("dealTime")),
                    cursor.getInt(cursor.getColumnIndex("count")),
                    cursor.getFloat(cursor.getColumnIndex("price"))*
                            cursor.getInt(cursor.getColumnIndex("count")),
                    context.getResources().getIdentifier(cursor.getString(cursor.getColumnIndex("cover")),"drawable","com.example.ebookstore")
            );
            list.add(historyBean);
        }

    }
}