package com.example.finalexer1grp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.navigation.fragment.NavHostFragment;
import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.productImagesView);

        String[] names = getResources().getStringArray(R.array.product_names);
        int itemCount = names.length;
        TextView numItemsView = view.findViewById(R.id.numItemsView);
        numItemsView.setText(getString(R.string.items_count_template, itemCount));

        String[] prices = getResources().getStringArray(R.array.product_prices);
        String[] descriptions = getResources().getStringArray(R.array.product_descriptions);

        android.content.res.TypedArray images = getResources().obtainTypedArray(R.array.product_images);

        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            double price = Double.parseDouble(prices[i]);
            String desc = descriptions[i];

            int imageResId = images.getResourceId(i, -1);

            productList.add(new Product(name, price, imageResId, desc));
        }
        images.recycle();

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ProductAdapter adapter = new ProductAdapter(productList, (position, v) -> {
            Product currentProduct = productList.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("name", currentProduct.getName());
            bundle.putDouble("price", currentProduct.getPrice());
            bundle.putString("desc", currentProduct.getDescription());
            bundle.putInt("image", currentProduct.getImgRes());

            NavHostFragment.findNavController(ProductFragment.this)
                    .navigate(R.id.action_productFragment_to_prodViewFragment, bundle);
        });
        recyclerView.setAdapter(adapter);
    }
}