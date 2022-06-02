package com.example.bookshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    public static UserDao user;
    public static HistoryFragment newInstance(UserDao userDao) {
        HistoryFragment fragment = new HistoryFragment();
        user=userDao;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView listView= (ListView)getView().findViewById(R.id.listview);
        List<HistoryBean> list=new ArrayList<>();
//        user.addOrder("2002-4-16","orders",1,30);
//        user.getOrder(list);
        init(list);
        HistoryAdapter historyAdapter=new HistoryAdapter(getActivity(),R.layout.list,list);
        listView.setAdapter(historyAdapter);
    }

    private void init(List<HistoryBean> list)
    {
        HistoryBean bean1=new HistoryBean("人类简史：从动物到上帝","1","2022-6-2"
        ,1, (float) 23.3,R.drawable.love);
        HistoryBean bean2=new HistoryBean("简·爱","2","2022-6-2"
                ,2,106,R.drawable.gone);
        list.add(bean1);
        list.add(bean2);
    }
}

