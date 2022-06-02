package com.example.bookshop;

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
import java.util.List;

public class CartAdapter extends ArrayAdapter<CartBean> {

    private ViewHolder viewHolder;
    private final int imageId;

    public CartAdapter(@NonNull Context context, int resource, @NonNull List<CartBean> objects) {
        super(context, resource, objects);
        this.imageId = resource;
    }

    public static class ViewHolder {
        public ImageView bookCover;
        public TextView bookTitle;
        public TextView price;
        public TextView count;
        public Button addBtn;
        public Button subBtn;
        public TextView sumPrice;
        public Button deleteBtn;
        public Button buyBtn;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (null != convertView)
        {
            viewHolder = (ViewHolder) convertView.getTag();
            return convertView;
        }

        viewHolder = new ViewHolder();
        CartBean bean = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(imageId, parent, false);

        DecimalFormat formatter = new DecimalFormat("0.00");

        viewHolder.bookCover = convertView.findViewById(R.id.book_cover_cart);
        viewHolder.bookTitle = convertView.findViewById(R.id.book_title_cart);
        viewHolder.price = convertView.findViewById(R.id.price_cart);
        viewHolder.count = convertView.findViewById(R.id.count_cart);
        viewHolder.sumPrice = convertView.findViewById(R.id.sum_price_cart);
        viewHolder.addBtn = convertView.findViewById(R.id.add_btn_cart);
        viewHolder.subBtn = convertView.findViewById(R.id.sub_btn_cart);
        viewHolder.buyBtn = convertView.findViewById(R.id.buy_cart);

        viewHolder.bookCover.setImageResource(bean.getImageID());
        viewHolder.bookTitle.setText(bean.getName());
        viewHolder.price.setText(formatter.format(bean.getPrice()));
        viewHolder.count.setText(String.valueOf(bean.getCount()));
        float sumPrice = bean.getPrice() * bean.getCount() - bean.getDiscount();
        viewHolder.sumPrice.setText(formatter.format(sumPrice));

        convertView.setTag(viewHolder);
        return convertView;
    }

}
