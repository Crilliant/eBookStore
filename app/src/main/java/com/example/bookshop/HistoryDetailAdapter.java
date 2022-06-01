package com.example.bookshop;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class HistoryDetailAdapter extends ArrayAdapter {
    private final int ImageId;

    public HistoryDetailAdapter(@NonNull Context context, int resource, List<HistoryBean> obj) {
        super(context, resource,obj);
        this.ImageId=resource;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HistoryBean myBean = (HistoryBean) getItem(position);
        View view;
        DetailHolder holder;
        if(convertView==null)//如果convertView为空，则生成新对象
        {
            view = LayoutInflater.from(getContext()).inflate(ImageId,null);//这个是实例化一个自己写的界面Item
            holder=new DetailHolder(view.findViewById(R.id.ll_view),view.findViewById(R.id.image),
                    view.findViewById(R.id.bookName),view.findViewById(R.id.quantity),
                    view.findViewById(R.id.total));
            view.setTag(holder);
        }//否则将其取出
        else
        {
            view=convertView;
            holder=(DetailHolder) view.getTag();
        }
        holder.image.setImageResource(myBean.getImageID());
        holder.name.setText(myBean.getName());
        holder.total.setText("总价格:"+myBean.getTotal());
        holder.quantity.setText("购入数量:"+myBean.getQuantity()+"");
//        holder.linearLayout.setOnClickListener(View->{//检查哪一项被点击了
//            Intent intent=new Intent();
//            intent.setClass(getContext(),HistoryDetailActivity.class);
//            ContextCompat.startActivity(getContext(),intent,null);
//        });
        return view;
    }
}

class DetailHolder
{
    LinearLayout linearLayout;
    ImageView image;
    TextView name;
    TextView quantity;
    TextView total;
    public DetailHolder(LinearLayout linearLayout,ImageView image, TextView name, TextView quantity,TextView total)
    {
        this.linearLayout=linearLayout;
        this.image=image;
        this.name=name;
        this.quantity=quantity;
        this.total=total;
    }
}


