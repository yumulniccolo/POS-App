package com.example.finalexer1grp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;           // ← ADD THIS
import android.widget.TextView;
import android.widget.Toast;          // ← ADD THIS
import androidx.appcompat.app.AlertDialog;  // ← ADD THIS

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

        // Display total
        TextView tvTotal = view.findViewById(R.id.tvTotal);
        tvTotal.setText(CartManager.getInstance().getFormattedTotal());

        // ← ADD THIS: Place Order button code
        Button btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Confirm Order")
                    .setMessage("Are all orders correct?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        CartManager.getInstance().getCartItems().clear();
                        tvTotal.setText("₱ 0.00");
                        recyclerView.getAdapter().notifyDataSetChanged();
                        Toast.makeText(requireContext(), "Order is placed! Please wait for delivery.", Toast.LENGTH_LONG).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().findViewById(R.id.shopToolbar).setVisibility(View.GONE);

        TextView tvTotal = requireView().findViewById(R.id.tvTotal);
        tvTotal.setText(CartManager.getInstance().getFormattedTotal());
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().findViewById(R.id.shopToolbar).setVisibility(View.VISIBLE);
    }
}