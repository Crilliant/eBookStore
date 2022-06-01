package com.example.bookshop;

public class HistoryBean {
    String name;//书名
    String shoppingID;//订单编号
    String time;//购买时间
    int quantity;//图书数目
    float total;//总价格
    int imageID;//图片

    public HistoryBean(String name, String shoppingID, String time, int quantity,float total, int imageID)
    {
        this.name=name;
        this.shoppingID=shoppingID;
        this.time=time;
        this.quantity=quantity;
        this.total=total;
        this.imageID=imageID;
    }
//    public void setName(String name)
//    {
//        this.name=name;
//    }
//
//    public void setTime(String time){ this.time=time; }
//
//    public void setImageID(int imageID)
//    {
//        this.imageID=imageID;
//    }

    public String getName()
    {
        return name;
    }

    public String getTime(){
        return time;
    }


    public int getImageID()
    {
        return imageID;
    }

    public String getShoppingID()
    {
        return shoppingID;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public float getTotal(){
        return total;
    }

}
