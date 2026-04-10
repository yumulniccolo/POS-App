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

    public ProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.productImagesView);

        // This is where we call our string.xml
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(getString(R.string.prodName0), 2500.00, R.drawable.game_and_watch_ball));
        productList.add(new Product(getString(R.string.prodName1), 6200.00, R.drawable.sega_game_gear_wb));
        productList.add(new Product(getString(R.string.prodName2), 8600.00, R.drawable.nintendo_game_boy_advance_purple_fl));
        productList.add(new Product(getString(R.string.prodName3), 7800.00, R.drawable.nintendo_ds_lite_black_open));
        productList.add(new Product(getString(R.string.prodName4), 17000.00, R.drawable.playstation_vita_1101_fl));
        productList.add(new Product(getString(R.string.prodName5), 35000.00, R.drawable.steamdeck));
        productList.add(new Product(getString(R.string.prodName6), 12300.00, R.drawable.nintendo_3ds_aquaopen));
        productList.add(new Product(getString(R.string.prodName7), 4000.00, R.drawable.psp_1000));
        productList.add(new Product(getString(R.string.prodName8), 16990.00, R.drawable.nintendo_switch_wjoycons_blrd_standing_fl));

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ProductAdapter adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);
    }
}