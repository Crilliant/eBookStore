/*
 * Created by cyx on 2022.5.22
 *
 *  */
package com.example.ebookstore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends ArrayAdapter<CartBean> {

    private ViewHolder viewHolder;
    private final ArrayList<ViewHolder> viewHolderMap;
    private final int imageId;
    private final CartFragment fragment;
    private final List<CartBean> obj;

    @Override
    public int getCount() {
        return obj.size();
    }

    @Override
    public CartBean getItem(int position) {
        return obj.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public CartAdapter(@NonNull Context context, int resource, @NonNull List<CartBean> objects, CartFragment fragment) {
        super(context, resource, objects);
        this.imageId = resource;
        this.fragment = fragment;
        this.obj = objects;
        this.viewHolderMap = new ArrayList<>();
    }

    public static class ViewHolder {
        public ImageView bookCover;
        public TextView bookTitle;
        public TextView price;
        public TextView count;
        public Button addBtn;
        public Button subBtn;
        public TextView sumPrice;
        public Button buyBtn;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        DecimalFormat formatter = new DecimalFormat("0.00");

        if (null != convertView)
        {
            viewHolder = (ViewHolder) convertView.getTag();
            //return convertView;
        } else {
            viewHolder = new ViewHolder();
            viewHolderMap.add(viewHolder);
            convertView = LayoutInflater.from(getContext()).inflate(imageId, parent, false);

            viewHolder.bookCover = convertView.findViewById(R.id.book_cover_cart);
            viewHolder.bookTitle = convertView.findViewById(R.id.book_title_cart);
            viewHolder.price = convertView.findViewById(R.id.price_cart);
            viewHolder.count = convertView.findViewById(R.id.count_cart);
            viewHolder.sumPrice = convertView.findViewById(R.id.sum_price_cart);
            viewHolder.addBtn = convertView.findViewById(R.id.add_btn_cart);
            viewHolder.subBtn = convertView.findViewById(R.id.sub_btn_cart);
            viewHolder.buyBtn = convertView.findViewById(R.id.buy_cart);

            viewHolder.addBtn.setOnClickListener(view -> {
                CartBean item = getItem(position);
                int count = item.getCount();
                item.setCount(count + 1);
                ViewHolder newView = viewHolderMap.get(position);
                newView.count.setText(String.valueOf(item.getCount()));
                float newSumPrice = item.getPrice() * item.getCount() - item.getDiscount();
                newView.sumPrice.setText(formatter.format(newSumPrice));
                notifyDataSetChanged();
            });

            viewHolder.subBtn.setOnClickListener(view -> {
                CartBean item = getItem(position);
                int count = item.getCount();
                item.setCount(count - 1);
                ViewHolder newView = viewHolderMap.get(position);
                newView.count.setText(String.valueOf(item.getCount()));
                float newSumPrice = item.getPrice() * item.getCount() - item.getDiscount();
                newView.sumPrice.setText(formatter.format(newSumPrice));
                if (count <= 1)
                {
                    obj.remove(position);
                    viewHolderMap.remove(position);
                }
                notifyDataSetChanged();
            });

            viewHolder.buyBtn.setOnClickListener(view -> fragment.showDialog());

            convertView.setTag(viewHolder);
        }

        CartBean bean = obj.get(position);

        viewHolder.bookCover.setImageResource(bean.getImageID());
        viewHolder.bookTitle.setText(bean.getName());
        viewHolder.price.setText(formatter.format(bean.getPrice()));
        viewHolder.count.setText(String.valueOf(bean.getCount()));
        float sumPrice = bean.getPrice() * bean.getCount() - bean.getDiscount();
        viewHolder.sumPrice.setText(formatter.format(sumPrice));
        return convertView;
    }

}
