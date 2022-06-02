package com.example.bookshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        //HistoryAdapter historyAdapter=new HistoryAdapter(getActivity(),R.layout.list,list);
        CartAdapter cartAdapter = new CartAdapter(getActivity(), R.layout.cart_item, list);
        listView.setAdapter(cartAdapter);
    }

    private void init(List<CartBean> list)
    {
        CartBean bean1=new CartBean("玩具",1,R.drawable.wanju,4
                ,100,0);
        CartBean bean2=new CartBean("香水",2,R.drawable.xiangshui,5
                ,200,0);
        list.add(bean1);
        list.add(bean2);
    }
}