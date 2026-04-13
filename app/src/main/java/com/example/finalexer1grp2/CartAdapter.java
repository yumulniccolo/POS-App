package com.example.finalexer1grp2;

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

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.cartName.setText(item.name);
        holder.cartPrice.setText(String.format("₱%.2f", item.getTotalPrice()));
        holder.cartQty.setText(String.valueOf(item.quantity));
        holder.cartImage.setImageResource(item.imageRes);

        holder.deleteBtn.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
        });

        holder.addBtn.setOnClickListener(v -> {
            item.quantity++;
            holder.cartQty.setText(String.valueOf(item.quantity));
            holder.cartPrice.setText(String.format("₱%.2f", item.getTotalPrice()));

            // TODO: listener to update the Fragment's Subtotal
        });

        holder.minusBtn.setOnClickListener(v -> {
            if (item.quantity > 1) {
                item.quantity--;
                holder.cartQty.setText(String.valueOf(item.quantity));
                holder.cartPrice.setText(String.format("₱%.2f", item.getTotalPrice()));

            // Call listener here
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cartName, cartPrice, cartQty;
        ImageView cartImage;
        ImageButton deleteBtn, addBtn, minusBtn;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartName  = itemView.findViewById(R.id.item_name_cart);
            cartPrice = itemView.findViewById(R.id.item_price_cart);
            cartQty   = itemView.findViewById(R.id.item_qty_cart);
            cartImage = itemView.findViewById(R.id.item_img_cart);
            deleteBtn = itemView.findViewById(R.id.dlt_btn);
            addBtn    = itemView.findViewById(R.id.add_btn_cart);
            minusBtn  = itemView.findViewById(R.id.minus_btn_cart);
        }
    }
}