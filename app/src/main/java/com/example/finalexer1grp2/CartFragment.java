package com.example.finalexer1grp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

// ← DAGDAG: Implement yung interface from CartAdapter
public class CartFragment extends Fragment implements CartAdapter.OnCartChangeListener {

    private RecyclerView recyclerView;  // ← DAGDAG: Instance variable para ma-access sa methods
    private TextView tvTotal;           // ← DAGDAG: Instance variable

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

        recyclerView = view.findViewById(R.id.cartRecyclerview);  // ← PALITAN: remove "RecyclerView "
        List<CartItem> items = CartManager.getInstance().getCartItems();

        // ← PALITAN: Dagdagan ng "this" parameter para sa interface
        CartAdapter adapter = new CartAdapter(items, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Display total
        tvTotal = view.findViewById(R.id.tvTotal);  // ← PALITAN: remove "TextView "
        tvTotal.setText(CartManager.getInstance().getFormattedTotal());

        // Place Order button code - UPDATED with empty cart check and invoice
        Button btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(v -> {
            // ← DAGDAG: Check if cart is empty
            if (CartManager.getInstance().getCartItems().isEmpty()) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Cart is Empty")
                        .setMessage("Please add items to cart first.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            // ← DAGDAG: Build invoice summary
            StringBuilder invoice = new StringBuilder();
            invoice.append("Order Summary:\n\n");

            for (CartItem item : CartManager.getInstance().getCartItems()) {
                invoice.append(item.name)
                        .append(" x")
                        .append(item.quantity)
                        .append(" = ₱")
                        .append(String.format("%,.2f", item.getTotalPrice()))
                        .append("\n");
            }

            invoice.append("\n--------------------\n");
            invoice.append("Total: ")
                    .append(CartManager.getInstance().getFormattedTotal());

            // Show confirmation with invoice
            new AlertDialog.Builder(requireContext())
                    .setTitle("Confirm Order")
                    .setMessage(invoice.toString())
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

    // ← DAGDAG: Implement yung interface method para real-time update
    @Override
    public void onItemDeleted() {
        tvTotal.setText(CartManager.getInstance().getFormattedTotal());
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().findViewById(R.id.shopToolbar).setVisibility(View.GONE);

        // Update total when returning to screen
        if (tvTotal != null) {
            tvTotal.setText(CartManager.getInstance().getFormattedTotal());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().findViewById(R.id.shopToolbar).setVisibility(View.VISIBLE);
    }
}