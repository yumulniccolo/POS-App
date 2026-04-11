package com.example.finalexer1grp2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class viewProduct extends Fragment {

    public viewProduct() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.pName);
        TextView price = view.findViewById(R.id.pPrice);
        TextView desc = view.findViewById(R.id.pDescription);
        ImageView image = view.findViewById(R.id.imageView);

        if (getArguments() != null) {
            name.setText(getArguments().getString("name"));
            price.setText(String.format("₱%.2f", getArguments().getDouble("price")));
            desc.setText(getArguments().getString("desc"));
            image.setImageResource(getArguments().getInt("image"));

            desc.setText(
                Html.fromHtml(
                    getArguments().getString("desc"),
                    Html.FROM_HTML_MODE_LEGACY
                    )
            );
        }
    }
}