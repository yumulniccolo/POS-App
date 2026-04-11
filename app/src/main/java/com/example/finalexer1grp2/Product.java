package com.example.finalexer1grp2;

// Initialize variables
public class Product {
    public String name;
    public double price;
    public int imgRes;
    public String description;

    // Constructor for variables
    public Product(String name, double price, int imgRes, String description) {
        this.name = name;
        this.price = price;
        this.imgRes = imgRes;
        this.description = description;
    }
        // Getters to return the product data
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
