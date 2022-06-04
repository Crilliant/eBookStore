/*
 * Created by cyx on 2022.5.22
 *
 *  */
package com.example.ebookstore;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    UserDao userDao = new UserDao(getContext());

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView listView = (ListView)getView().findViewById(R.id.listview);
        List<CartBean> list=new ArrayList<>();
        init(list);
        CartAdapter cartAdapter = new CartAdapter(getActivity(), R.layout.cart_item, list, this);
        listView.setAdapter(cartAdapter);
    }

    private void init(List<CartBean> list)
    {
        CartBean bean1=new CartBean("第一行代码",7,R.drawable.tea,4
                , 14.39f,0);
        CartBean bean2=new CartBean("第二行代码",8,R.drawable.call,5
                ,15.3F,0);
        CartBean bean3=new CartBean("第三行代码",9,R.drawable.love,1
                ,88,0);
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
    }

    public void showDialog(){
        AlertDialog.Builder newDialog = new AlertDialog.Builder(getContext());
        newDialog.setTitle("网上书店");
        newDialog.setIcon(R.mipmap.ic_launcher_round);
        newDialog.setMessage("确认支付？");

        //设置按钮
        newDialog.setPositiveButton("确定"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity()
                                ,"支付成功，已加入购买历史记录",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        newDialog.setNegativeButton("取消"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(),
                                "已取消支付",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

        newDialog.create().show();
    }

}