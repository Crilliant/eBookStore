// book结构体，用于booklist展示
// Created by cyx
package com.example.ebookstore;

public class Book {
    private String title, author, detail,cover;
    private  int bookID,  classNum;
    private float price;
    Book(int id,String title, String author, String cover, float price){
        bookID = id;
        this.title = title;
        this.author = author;
        this.cover = cover;
        this.price = price;
    }
    Book(int id, String title, String author, String detail, String cover, int classNum, float price){
        this.title = title;
        this.author = author;
        this.detail = detail;
        this.cover = cover;
        this.classNum = classNum;
        this.price = price;
        bookID =id;
    }

    public String getTitle(){return title;}
    public String getAuthor(){return author;}
    public String getDetail(){return detail;}
    public int getBookID(){return bookID;}
    public String getCover(){return  cover;}
    public int getClassNum(){return classNum;}
    public float getPrice(){return price;}
}
