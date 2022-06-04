/*
 * Created by cyx on 2022.5.22
 *
 *  */
package com.example.ebookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText account;
    EditText password;
    EditText configure;
    Button register;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init()
    {
        findView();
        setControl();
    }

    private void findView()
    {
        account=findViewById(R.id.account);
        password=findViewById(R.id.password);
        configure=findViewById(R.id.configure);
        register=findViewById(R.id.register);
        userDao=new UserDao(RegisterActivity.this);
    }

    private void setControl()
    {
        register.setOnClickListener(View->{
            if(!userDao.userExist(account.getText().toString()))
            {
                if(password.getText().toString().equals(configure.getText().toString()))
                {
                    userDao.insert(account.getText().toString(),password.getText().toString(),"user");
                    userDao.newHistory(account.getText().toString());
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(RegisterActivity.this,"用户名已存在",Toast.LENGTH_LONG).show();
            }
        });
    }
}