package com.example.finalexer1grp2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar shopToolbar = findViewById(R.id.shopToolbar);

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
}
