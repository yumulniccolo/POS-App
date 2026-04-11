package com.example.finalexer1grp2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class viewProduct extends Fragment {

    private int quantity = 1;
    public viewProduct() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_product, container, false);
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
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.pName);
        TextView price = view.findViewById(R.id.pPrice);
        TextView desc = view.findViewById(R.id.pDescription);
        ImageView image = view.findViewById(R.id.imageView);
        MaterialToolbar toolbar = view.findViewById(R.id.fragment_cart_toolbar);

        ImageButton minusBtn = view.findViewById(R.id.minusBtn);
        ImageButton addBtn = view.findViewById(R.id.addBtn);
        TextView tvQuantity = view.findViewById(R.id.tvQuantity);
        Button btnAddToCart = view.findViewById(R.id.btnAddToCart);

        if (getArguments() != null) {
            name.setText(getArguments().getString("name"));
            price.setText(String.format("₱%.2f", getArguments().getDouble("price")));
            desc.setText(getArguments().getString("desc"));
            image.setImageResource(getArguments().getInt("image"));
            toolbar.setTitle(getArguments().getString("name"));

            desc.setText(
                Html.fromHtml(
                    getArguments().getString("desc"),
                    Html.FROM_HTML_MODE_LEGACY
                    )
            );
        }

        toolbar.setNavigationOnClickListener(v ->
                requireActivity().getOnBackPressedDispatcher().onBackPressed()
        );

        addBtn.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        minusBtn.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        btnAddToCart.setOnClickListener(v -> {
            String productName = getArguments().getString("name");

            Toast.makeText(requireContext(), quantity + " " + productName + " added to cart", Toast.LENGTH_SHORT).show();

            requireActivity()
                    .getOnBackPressedDispatcher()
                    .onBackPressed();
        });
    }
}