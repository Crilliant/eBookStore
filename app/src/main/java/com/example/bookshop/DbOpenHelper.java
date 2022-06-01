package com.example.bookshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper {
    //数据库版本号
    private static Integer Version = 1;
//    Context context;
// 初始化book table的数据, 3类书
    String initBookData1 =
            "insert into book \n" +
                    "values\n" +
                    "(100, 0, '历史', 0, '', '', ''),\n" +
                    "(1, 0, '人类简史：从动物到上帝', 23.3, 'love', '尤瓦尔·赫拉利', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(2, 0, '娱乐至死', 53.3, 'ai', '余华', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(101, 1, '哲学', 0, '', '', ''),\n" +
                    "(3, 1, '瓦尔登湖', 4.39, 'play', '尤瓦尔·赫拉利', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(4, 1, '类物到上帝', 5.3, 'ai', '尤瓦利', '用不太长的文字，讲述了主人公福贵五十年'),"+
                    "(103, 3, '编程', 0, '', '', ''),\n" +
                    "(7, 3, '第一行代码', 14.39, 'tea', '尤瓦尔·赫拉利', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(8, 3, '第二行代码', 15.3, 'call', '尤瓦利', '用不太长的文字，讲述了主人公福贵五十年'),"+
                    "(102, 2, '科幻', 0, '', '', ''),\n" +
                    "(5, 2, '三体', 49, 'forest', '尤赫拉利', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(6, 2, '简·爱', 53, 'gone', '尤瓦利', '用不太长的文字，讲述了主人公福贵五十年')";

    //创建book table
    String book_sql = "CREATE TABLE book(\n" +
            "   bookID       INT     PRIMARY KEY     NOT NULL,\n" +
            "   classNum    INT    NOT NULL,\n" +
            "   title    text    not null,\n" +
            "   price    REAL    not null,\n" +
            "   cover    text,   \n" +
            "   author   text,\n" +
            "   detail   text\n" +
            ")";
    // 创建user table
    String user_sql="CREATE TABLE user(\n" +
            "   account text primary key not null,\n" +
            "   psw text not null\n" +
            ")";

    // 创建order table
    String order_sql="CREATE TABLE orders(\n" +
            "   orderID int not null,\n" +
            "   dealTime text not null,\n" +
            "   bookID int,\n" +
            "   count int   \n" +
            ")";

    // 创建购物车表
    String cart_sql="CREATE TABLE cart(\n" +
            "   bookID int,\n" +
            "   count int \n" +
            ")";
    // 建库
    public DbOpenHelper(Context context) {

        super(context, "eBookStore.db", null, 1);
    }


    // 建库后调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建 book table
        db.execSQL(book_sql);
        // 初始化book table 数据
        db.execSQL(initBookData1);

        // 创建user table
        db.execSQL(user_sql);
        // 创建 order table
        db.execSQL(order_sql);
        // 创建购物车表
        db.execSQL(cart_sql);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

