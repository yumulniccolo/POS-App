package com.example.finalexer1grp2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.badge.ExperimentalBadgeUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BadgeDrawable cartBadge;

    @ExperimentalBadgeUtils
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar shopToolbar = findViewById(R.id.shopToolbar);
        shopToolbar.post(() -> {
            BadgeDrawable badge = BadgeDrawable.create(this);
            badge.setVisible(true);
            badge.setNumber(0);

            BadgeUtils.attachBadgeDrawable(
                    badge,
                    shopToolbar,
                    R.id.CartBtn
            );

            this.cartBadge = badge;
        });

        shopToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.CartBtn) {
                NavHostFragment navHostFragment =
                        (NavHostFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragmentContainerView);

                if (navHostFragment != null) {
                    NavController navController = navHostFragment.getNavController();

                    if (navController.getCurrentDestination() != null &&
                            navController.getCurrentDestination().getId() == R.id.productFragment) {
                        navController.navigate(R.id.action_productFragment_to_cartFragment);
                    }
                }
                return true;
            }
            return false;
        });
    }

    public void updateCartBadge(List<CartItem> cartItems) {
        int totalQty = 0;

        for (CartItem item : cartItems) {
            totalQty += item.quantity;
        }

        if (cartBadge != null) {
            cartBadge.setNumber(totalQty);
            cartBadge.setVisible(true);
        }
    }
}
