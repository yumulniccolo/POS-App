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
        holder.cartQty.setText("QTY " + item.quantity);
        holder.cartImage.setImageResource(item.imageRes);

        holder.deleteBtn.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
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
            cartName  = itemView.findViewById(R.id.textView2);
            cartPrice = itemView.findViewById(R.id.textView6);
            cartQty   = itemView.findViewById(R.id.textView7);
            cartImage = itemView.findViewById(R.id.imageView2);
            deleteBtn = itemView.findViewById(R.id.imageButton2);
        }
    }
}