package com.example.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the main entry point for the application.
 * It is responsible for displaying the main menu and handling the user's navigation choices.
 *
 * @author Jizhou Yang
 *
 */


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton buttonSpecialtyPizzas = findViewById(R.id.button_specialty_pizzas);
        ImageButton buttonBuildYourOwn = findViewById(R.id.button_build_your_own);
        ImageButton buttonCurrentOrder = findViewById(R.id.button_current_order);
        ImageButton buttonStoreOrders = findViewById(R.id.button_store_orders);


        buttonSpecialtyPizzas.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this, SpecialtyPizzasActivity.class));
        });


        buttonBuildYourOwn.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this, BuildYourOwnActivity.class));
        });


        buttonCurrentOrder.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this, CurrentOrderActivity.class));
        });


        buttonStoreOrders.setOnClickListener(v -> {

            startActivity(new Intent(MainActivity.this, StoreOrdersActivity.class));
        });
    }
}
