// 用于设置booklist每个条目的数据填充
// Created by cyx
package com.example.bookshop;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// 用于设置条目展示的形式
public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.BookViewHolder> {
    List<Book> bookData;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    Context context;

    // data is passed into the constructor
    BookRecyclerViewAdapter(Context context, List<Book> data) {
        this.inflater = LayoutInflater.from(context);
        this.bookData = data;
        this.context = context;
    }


    // inflates the row layout from xml when needed => 传入布局
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.book_row_item, parent, false);
        return new BookViewHolder(view);
    }

    // 显示数据
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book b = bookData.get(position);
        if(b.getPrice()==0){
            holder.coverView.setVisibility(View.INVISIBLE);
            holder.authorView.setVisibility(View.INVISIBLE);
            holder.priceView.setVisibility(View.INVISIBLE);

            // 将item高度设置为50dp,修改背景颜色
            ViewGroup.LayoutParams params =(ViewGroup.LayoutParams) holder.rowLayout.getLayoutParams();
            params.height=(int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
            holder.rowLayout.setLayoutParams(params);
            holder.rowLayout.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
            holder.titleView.setText(b.getTitle());

        }else {
//            holder.coverView.setVisibility(View.VISIBLE);
//            holder.authorView.setVisibility(View.VISIBLE);
//            holder.priceView.setVisibility(View.VISIBLE);
            // 根据book的cover（即图片名字）来查询资源

            int id = context.getResources().getIdentifier(b.getCover(),"drawable","com.example.bookshop");
            Drawable img = context.getDrawable(id);
            holder.coverView.setImageDrawable(img);

            holder.titleView.setText(b.getTitle());
            holder.authorView.setText(b.getAuthor());
            holder.priceView.setText(String.format("%.2f", b.getPrice()));
        }
    }
    // total number of rows
    @Override
    public int getItemCount() {
        return bookData.size();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    // convenience method for getting data at click position
    Book getItem(int id) {
        return bookData.get(id);
    }

    // stores and recycles views as they are scrolled off screen
    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleView, authorView, priceView;
        ImageView coverView;
        LinearLayout rowLayout;

        BookViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.BookTitle);
            authorView = itemView.findViewById(R.id.BookAuthor);
            priceView = itemView.findViewById(R.id.BookPrice);
            coverView = itemView.findViewById(R.id.BookCover);
            rowLayout = itemView.findViewById(R.id.ItemRow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(view, getAdapterPosition());
        }
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}





