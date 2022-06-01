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

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
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
        HistoryBean bean1=new HistoryBean("玩具","1","2022-5-14"
        ,1,30,R.drawable.wanju);
        HistoryBean bean2=new HistoryBean("香水","2","2022-5-23"
                ,1,30,R.drawable.xiangshui);
        list.add(bean1);
        list.add(bean2);
    }
}

