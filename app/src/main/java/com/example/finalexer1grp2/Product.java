package com.example.finalexer1grp2;

public class Product {
    public String name;
    public double price;
    public int imgRes;
    public String description;
    public Product(String name, double price, int imgRes, String description) {
        this.name = name;
        this.price = price;
        this.imgRes = imgRes;
        this.description = description;
    }
        public String getName() {
            return name;
        }
        public double getPrice() {
            return price;
        }
        public int getImgRes() {
            return imgRes;
        }
}
