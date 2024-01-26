package com.example.pizza;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import androidx.recyclerview.widget.DividerItemDecoration;






public class SpecialtyPizzasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PizzaAdapter adapter;
    private List<PizzaItem> pizzaItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_pizzas);

        recyclerView = findViewById(R.id.specialty_pizzas_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pizzaItems = new ArrayList<>();

        pizzaItems.add(new PizzaItem("deluxe","Deluxe", "Sausage, pepperoni, green pepper, onion, mushroom","tomato",  14.99));
        pizzaItems.add(new PizzaItem("supreme","Supreme", "Sausage, pepperoni, ham, green pepper, onion, black olive, mushroom","tomato", 15.99));
        pizzaItems.add(new PizzaItem("meatzza","Meatzza", "Sausage, pepperoni, beef, ham","tomato", 16.99));
        pizzaItems.add(new PizzaItem("seafood","Seafood", "Shrimp, squid, crab meats","alfredo", 17.99));
        pizzaItems.add(new PizzaItem("pepperoni","Pepperoni", "pepperoni","tomato", 10.99));
        pizzaItems.add(new PizzaItem("capricciocrush","CapriccioCrush", "cherry tomatos, basil leaves, balsamic glaze","tomato", 16.99));
        pizzaItems.add(new PizzaItem("buffaloranch","BuffaloRanch", "spicy buffalo chicken, red bell peppers, red onions, jalapenos","tomato", 15.99));
        pizzaItems.add(new PizzaItem("bbqfusion","BBQFusion", "grilled chicken chunks, caramelized onions, pineapple chunks, spinach","tomato", 14.99));
        pizzaItems.add(new PizzaItem("pestoparadise","PestoParadise", "grilled prawns, cherry tomatos, red onions, pine nuts","tomato", 15.99));
        pizzaItems.add(new PizzaItem("mediveggiedelight","MediVeggieDelight", "sun dried tomatos, artichoke hearts, kalamata olives, spinach","tomato", 16.99));


        adapter = new PizzaAdapter(this, pizzaItems);
        recyclerView.setAdapter(adapter);



        Button goBackButton = findViewById(R.id.btn_go_back);
        goBackButton.setOnClickListener(v -> finish());

    }


}

