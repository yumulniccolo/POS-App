package com.example.finalexer1grp2;

public class CartItem {
    public String name;
    public double price;
    public int quantity;
    public int imageRes;

    public CartItem(String name, double price, int quantity, int imageRes) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageRes = imageRes;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}