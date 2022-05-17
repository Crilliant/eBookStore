package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText account;
    EditText password;
    TextView log;
    TextView register;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
        String act=sp.getString("account","");
        String pwd=sp.getString("pwd","");
        account.setText(act);
        password.setText(pwd);
    }

    private void saveAccount()
    {
        SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("account",account.getText().toString());
        editor.putString("pwd",password.getText().toString());
        editor.commit();
    }

    private void init()
    {
        findView();
        setControl();
    }

    private void findView()//找到控件
    {
        account=findViewById(R.id.account);
        password=findViewById(R.id.password);
        log=findViewById(R.id.log);
        register=findViewById(R.id.register);
        userDao=new UserDao(MainActivity.this);
    }

    private void setControl()//设置点击控件之后的操作
    {
        log.setOnClickListener(View->{
            if(!userDao.userExist(account.getText().toString()))//用户名不存在
            {
                Toast.makeText(MainActivity.this,"用户名不存在",Toast.LENGTH_LONG).show();
                return;
            }
            if(!userDao.ifLogin(account.getText().toString(),password.getText().toString()))//用户名存在但没有登陆成功
            {
                Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                return;
            }
            ///activity图书列表
            saveAccount();
            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, TabActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        register.setOnClickListener(View->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

}