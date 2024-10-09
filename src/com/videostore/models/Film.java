package com.videostore.models;

public class Film {
    private String title;
    private double price;
    private int available;
    
    public Film(String title, double price, int available) {
        this.title = title;
        this.price = price;
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
    
    public String getTitle() {
        return title;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void settitle(String title) {
        this.title = title;
    }
}