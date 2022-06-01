package com.example.ebookstore;

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
                    "(1, 0, '人类简史：从动物到上帝', 23.3, 'love', '尤瓦尔·赫拉利', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(2, 0, '娱乐至死', 53.3, 'ai', '余华', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(3, 1, '瓦尔登湖', 4.39, 'play', '尤瓦尔·赫拉利', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(4, 1, '类物到上帝', 5.3, 'ai', '尤瓦利', '用不太长的文字，讲述了主人公福贵五十年'),"+
                    "(5, 2, '三体', 49, 'call', '尤赫拉利', '用不太长的文字，讲述了主人公福贵五十年'),\n" +
                    "(6, 2, '简·爱', 53, 'gone', '尤瓦利', '用不太长的文字，讲述了主人公福贵五十年')";

    // 建库
    public DbOpenHelper(Context context) {

        super(context, "eBookStore.db", null, 1);
//        this.context=context;
    }


    // 建库后调用
    @Override
    public void onCreate(SQLiteDatabase db) {
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

        db.execSQL(book_sql);
        Log.i("database","created book table");
        // 初始化book table 数据
        db.execSQL(initBookData1);
        Log.i("database","initial the book data");
        Log.w("drawable", "table: "+Integer.toString(R.drawable.gone ));
        // 创建user table

        // 创建 order table

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

