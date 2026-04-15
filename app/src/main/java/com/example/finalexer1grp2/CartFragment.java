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

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView tvTotal;

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

        recyclerView = view.findViewById(R.id.cartRecyclerview);
        List<CartItem> items = CartManager.getInstance().getCartItems();
        ((MainActivity) requireActivity()).updateCartBadge(items);

        CartAdapter adapter = new CartAdapter(items, (position, v) -> {
            CartItem item = items.get(position);
            if (v.getId() == R.id.dlt_btn) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Delete Item")
                        .setMessage("Are you sure you want to delete " + item.name + "?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            items.remove(position);
                            recyclerView.getAdapter().notifyItemRemoved(position);
                            recyclerView.getAdapter().notifyItemRangeChanged(position, items.size());
                            tvTotal.setText(CartManager.getInstance().getFormattedTotal());
                            ((MainActivity) requireActivity()).updateCartBadge(items);
                        })
                        .setNegativeButton("No", null)
                        .show();
            } else if (v.getId() == R.id.add_btn_cart) {
                item.quantity++;
                recyclerView.getAdapter().notifyItemChanged(position);
                tvTotal.setText(CartManager.getInstance().getFormattedTotal());
                ((MainActivity) requireActivity()).updateCartBadge(items);
            } else if (v.getId() == R.id.minus_btn_cart) {
                if (item.quantity > 1) {
                    item.quantity--;
                    recyclerView.getAdapter().notifyItemChanged(position);
                    tvTotal.setText(CartManager.getInstance().getFormattedTotal());
                    ((MainActivity) requireActivity()).updateCartBadge(items);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        tvTotal = view.findViewById(R.id.tvTotal);
        tvTotal.setText(CartManager.getInstance().getFormattedTotal());

        Button btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(v -> {

            if (CartManager.getInstance().getCartItems().isEmpty()) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Cart is Empty")
                        .setMessage("Please add items to cart first.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

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

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().findViewById(R.id.shopToolbar).setVisibility(View.GONE);

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