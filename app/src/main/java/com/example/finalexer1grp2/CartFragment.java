package com.example.finalexer1grp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class CartFragment extends Fragment {

    public CartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        MaterialToolbar cartToolbar = view.findViewById(R.id.fragment_cart_toolbar);
        cartToolbar.setNavigationOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(CartFragment.this);
            navController.navigate(R.id.action_cartFragment_to_productFragment);
        });

        RecyclerView recyclerView = view.findViewById(R.id.cartRecyclerview);
        List<CartItem> items = CartManager.getInstance().getCartItems();

        CartAdapter adapter = new CartAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().findViewById(R.id.shopToolbar).setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().findViewById(R.id.shopToolbar).setVisibility(View.VISIBLE);
    }
}