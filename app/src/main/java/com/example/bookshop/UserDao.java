package com.example.bookshop;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class UserDao {
    DbOpenHelper dbOpenHelper;

    public UserDao(Context context)
    {
        dbOpenHelper =new DbOpenHelper(context);
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

    public void getOrder(List<HistoryBean> list)
    {
        SQLiteDatabase db= dbOpenHelper.getReadableDatabase();
        Cursor cursor=db.query("orders",null,"*",null,
                null,null,null);
        if(cursor.getCount()==0)
        {
            return;
        }
        else //cursor决定行，默认在-1，所以要移动，然后可以根据键值对得到要取的列
        {
            while(cursor.moveToNext())
            {
                @SuppressLint
                        ("Range") Cursor book=db.query
                        ("book",null,"bookID=?",
                                new String[]{cursor.getString(cursor.getColumnIndex("bookID"))},
                        null,null,null);
                book.moveToFirst();
                HistoryBean historyBean=new HistoryBean(book.getString(book.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("orderID")),
                        cursor.getString(cursor.getColumnIndex("dealTime")),
                        cursor.getInt(cursor.getColumnIndex("count")),
                        cursor.getFloat(cursor.getColumnIndex("price"))*
                                cursor.getInt(cursor.getColumnIndex("count")),
                        get cursor.getString(cursor.getColumnIndex("cover"))
                        );
            }
            int column=cursor.getColumnIndex("pwd");
        }
    }

}
