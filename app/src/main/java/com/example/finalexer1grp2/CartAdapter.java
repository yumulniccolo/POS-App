package com.example.finalexer1grp2;

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
    private ClickListener clickListener;

    public CartAdapter(List<CartItem> cartItems, ClickListener clickListener) {
        this.cartItems = cartItems;
        this.clickListener = clickListener;
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
        holder.cartQty.setText(String.valueOf(item.quantity));
        holder.cartImage.setImageResource(item.imageRes);

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(position, v);
            }
        });

        holder.deleteBtn.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(position, v);
            }
        });

        holder.addBtn.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(position, v);
            }
        });

        holder.minusBtn.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(position, v);
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