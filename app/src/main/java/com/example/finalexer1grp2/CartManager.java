package com.example.finalexer1grp2;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems = new ArrayList<>();

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        for (CartItem existing : cartItems) {
            if (existing.name.equals(item.name)) {
                existing.quantity += item.quantity;
                return;
            }
        }
        cartItems.add(item);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getCartTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotalPrice();
        }
        return total;
    }
    public String getFormattedTotal() {
        double total = getCartTotal();
        return String.format("₱ %,.2f", total);
    }
}