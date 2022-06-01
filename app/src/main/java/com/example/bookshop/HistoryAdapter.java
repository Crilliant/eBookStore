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

public class HistoryAdapter extends ArrayAdapter {
    private final int ImageId;

    public HistoryAdapter(@NonNull Context context, int resource, List<HistoryBean> obj) {
        super(context, resource,obj);
        this.ImageId=resource;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HistoryBean myBean = (HistoryBean) getItem(position);
        View view;
        Holder holder;
        if(convertView==null)//如果convertView为空，则生成新对象
        {
            view = LayoutInflater.from(getContext()).inflate(ImageId,null);//这个是实例化一个自己写的界面Item
            holder=new Holder(view.findViewById(R.id.ll_view),view.findViewById(R.id.image),view.findViewById(R.id.shoppingID),
                    view.findViewById(R.id.name),view.findViewById(R.id.quantity),
                    view.findViewById(R.id.total),view.findViewById(R.id.time));
            view.setTag(holder);
        }//否则将其取出
        else
        {
            view=convertView;
            holder=(Holder)view.getTag();
        }
        holder.image.setImageResource(myBean.getImageID());
        holder.name.setText(myBean.getName());
        holder.total.setText(myBean.getTotal()+"");
        holder.time.setText("订单完成时间"+myBean.getTime());
        holder.quantity.setText(myBean.getQuantity()+"");
        holder.shoppingID.setText("订单编号"+myBean.getShoppingID());
        holder.linearLayout.setOnClickListener(View->{//检查哪一项被点击了
        Intent intent=new Intent();
        intent.setClass(getContext(),HistoryDetailActivity.class);
        ContextCompat.startActivity(getContext(),intent,null);
        });
        return view;
    }
}

class Holder
{
    LinearLayout linearLayout;
    ImageView image;
    TextView shoppingID;
    TextView name;
    TextView quantity;
    TextView total;
    TextView time;
    public Holder(LinearLayout linearLayout,ImageView image, TextView shoppingID,TextView name, TextView quantity,TextView total,TextView time)
    {
        this.linearLayout=linearLayout;
        this.image=image;
        this.name=name;
        this.shoppingID=shoppingID;
        this.quantity=quantity;
        this.time=time;
        this.total=total;
    }
}

