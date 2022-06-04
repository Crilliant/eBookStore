/*
 * Created by cyx on 2022.5.22
 *
 *  */
package com.example.ebookstore;

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
        init(list);
        HistoryAdapter historyAdapter=new HistoryAdapter(getActivity(),R.layout.list,list);
        listView.setAdapter(historyAdapter);
    }

    private void init(List<HistoryBean> list)
    {
        HistoryBean bean1=new HistoryBean("第三行代码","202001","2020-06-12"
        ,1, (float) 88,R.drawable.love);
        list.add(bean1);
    }
}

