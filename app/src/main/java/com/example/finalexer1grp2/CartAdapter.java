package com.example.finalexer1grp2;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private Context context;

    // ← DAGDAG: Interface para sa callback
    public interface OnCartChangeListener {
        void onItemDeleted();
    }

    private OnCartChangeListener listener;

    // ← DAGDAG: Constructor with listener parameter
    public CartAdapter(List<CartItem> cartItems, OnCartChangeListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.cartName.setText(item.name);
        holder.cartPrice.setText(String.format("₱%.2f", item.getTotalPrice()));
        holder.cartQty.setText("QTY " + item.quantity);
        holder.cartImage.setImageResource(item.imageRes);

        // ← DAGDAG: Delete with confirmation dialog
        holder.deleteBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Item")
                    .setMessage("Are you sure you want to delete " + item.name + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Remove item
                        cartItems.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, cartItems.size());

                        // ← DAGDAG: Call listener para ma-update yung subtotal sa Fragment
                        if (listener != null) {
                            listener.onItemDeleted();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cartName, cartPrice, cartQty;
        ImageView cartImage;
        ImageButton deleteBtn;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartName  = itemView.findViewById(R.id.item_name_cart);
            cartPrice = itemView.findViewById(R.id.item_price_cart);
            cartQty   = itemView.findViewById(R.id.item_qty_cart);
            cartImage = itemView.findViewById(R.id.item_img_cart);
            deleteBtn = itemView.findViewById(R.id.dlt_btn);
        }
    }
}