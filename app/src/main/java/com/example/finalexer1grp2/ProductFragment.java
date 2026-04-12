package com.example.finalexer1grp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.productImagesView);

        // This is where we call our string.xml
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(getString(R.string.prodName0), 10.00, R.drawable.rj45, getString(R.string.prodDesc0)));
        productList.add(new Product(getString(R.string.prodName1), 450.00, R.drawable.crimping_tool, getString(R.string.prodDesc1)));
        productList.add(new Product(getString(R.string.prodName2), 350.00, R.drawable.network_tester, getString(R.string.prodDesc2)));
        productList.add(new Product(getString(R.string.prodName3), 120.00, R.drawable.stripper, getString(R.string.prodDesc3)));
        productList.add(new Product(getString(R.string.prodName4), 15.00, R.drawable.rj45_passthru, getString(R.string.prodDesc4)));
        productList.add(new Product(getString(R.string.prodName5), 300.00, R.drawable.punch_down_tool, getString(R.string.prodDesc5)));
        productList.add(new Product(getString(R.string.prodName6), 250.00, R.drawable.lan_cutter, getString(R.string.prodDesc6)));
        productList.add(new Product(getString(R.string.prodName7), 25.00, R.drawable.cat6, getString(R.string.prodDesc7)));
        productList.add(new Product(getString(R.string.prodName8), 750.00, R.drawable.network_kit, getString(R.string.prodDesc8)));

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }
}