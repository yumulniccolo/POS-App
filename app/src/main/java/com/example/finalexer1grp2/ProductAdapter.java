package com.example.finalexer1grp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// TO-DO: Reina add the description variable here appropriately and figure out how to connect this adapter to your fragment

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product currentProduct = productList.get(position);

        holder.productName.setText(currentProduct.getName());
        // The \u20B1 is the Unicode for the Peso sign
        holder.productPrice.setText(String.format("\u20B1%.2f", currentProduct.getPrice()));
        holder.productImgRes.setImageResource(currentProduct.getImgRes());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        ImageView productImgRes;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.textViewProduct);
            productPrice = itemView.findViewById(R.id.textViewPrice);
            productImgRes = itemView.findViewById(R.id.imageViewProduct);

        }
    }
}
