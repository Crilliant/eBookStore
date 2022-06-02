package com.example.bookshop;

public class CartBean {
    private String name;//书名
    private int bookID;//图书编号
    private int imageID;//图片
    private int count;//图书数目
    private float price;//单价
    private float discount;//折扣

    public String getName() {
        return name;
    }

    public int getBookID() {
        return bookID;
    }

    public int getImageID() {
        return imageID;
    }

    public int getCount() {
        return count;
    }

    public float getPrice() {
        return price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setCount(int count) {
        if(count < 0)
            throw new IllegalArgumentException("count should be positive number");
        this.count = count;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public CartBean(String name, int bookID, int imageID, int count, float price, float discount)
    {
        if(count < 0)
            throw new IllegalArgumentException("count should be positive number");
        this.name = name;
        this.bookID = bookID;
        this.imageID = imageID;
        this.count = count;
        this.price = price;
        this.discount = discount;
    }
}
