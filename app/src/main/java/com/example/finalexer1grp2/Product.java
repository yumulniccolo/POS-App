package com.example.finalexer1grp2;

// TO-DO: Reina add Product Description variable here, then make a constructor, then make a getter for it

// Initialize variables
public class Product {
    private String name;
    private double price;
    private int imgRes;

    // Constructor for variables
    public Product(String name, double price, int imgRes) {
        this.name = name;
        this.price = price;
        this.imgRes = imgRes;
    }
        // Getters to return the product data
        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getImgRes() { return imgRes; }
}
