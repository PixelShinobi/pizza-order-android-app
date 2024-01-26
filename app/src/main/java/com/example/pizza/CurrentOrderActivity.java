package com.example.pizza;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;



public class CurrentOrderActivity extends AppCompatActivity {

    private ListView pizzaOrdersList;
    private Button removePizzaButton, placeOrderButton;
    private CurrentOrderAdapter pizzaOrdersAdapter;
    private TextView orderNumber, subtotal, salesTax, orderTotal;

    private int selectedPosition = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        initializeViews();
        updatePizzaOrdersList();
    }

    private void initializeViews() {
        orderNumber = findViewById(R.id.OrderNumber);
        subtotal = findViewById(R.id.tvSubtotal);
        salesTax = findViewById(R.id.tvSalesTax);
        orderTotal = findViewById(R.id.tvTotal);
        pizzaOrdersList = findViewById(R.id.PizzaOrdersList);
        if (pizzaOrdersList != null) {
            pizzaOrdersList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            pizzaOrdersList.setOnItemClickListener((parent, view, position, id) -> {
                selectedPosition = position;
                pizzaOrdersAdapter.setSelectedIndex(position);
            });
        }


        removePizzaButton = findViewById(R.id.btnRemovePizza);
        placeOrderButton = findViewById(R.id.btnPlaceOrder);
        Button goBackButton = findViewById(R.id.btnGoBack);

        if (removePizzaButton != null) {
            removePizzaButton.setOnClickListener(v -> handleRemovePizza());
        }
        if (placeOrderButton != null) {
            placeOrderButton.setOnClickListener(v -> handlePlaceOrder());
        }
        if (goBackButton != null) {
            goBackButton.setOnClickListener(v -> finish());
        }
    }


    private void updatePizzaOrdersList() {
        Order currentOrder = Order.getInstance();
        List<String> pizzaDescriptions = new ArrayList<>();
        for (Pizza pizza : currentOrder.getPizzas()) {
            pizzaDescriptions.add(pizza.getDescription());
        }
        pizzaOrdersAdapter = new CurrentOrderAdapter(this, android.R.layout.simple_list_item_1, pizzaDescriptions);
        pizzaOrdersList.setAdapter(pizzaOrdersAdapter);
        updateOrderSummary();
    }



    private void updateOrderSummary() {
        Order currentOrder = Order.getInstance();
        double subtotalValue = currentOrder.getTotalPrice();
        double tax = subtotalValue * 0.06625;
        double total = subtotalValue + tax;

        subtotal.setText(String.format("$%.2f", subtotalValue));
        salesTax.setText(String.format("$%.2f", tax));
        orderTotal.setText(String.format("$%.2f", total));
        orderNumber.setText(String.valueOf(currentOrder.getOrderId()));
    }

    private void handleRemovePizza() {
        int selectedIndex = pizzaOrdersList.getCheckedItemPosition();
        if (selectedIndex >= 0) {
            Order currentOrder = Order.getInstance();
            currentOrder.getPizzas().remove(selectedIndex);
            updatePizzaOrdersList();
        } else {


            Toast.makeText(this, getString(R.string.select_pizza_to_remove), Toast.LENGTH_SHORT).show();

        }
    }

    private void handlePlaceOrder() {
        StoreOrders storeOrders = StoreOrders.getInstance();
        Order currentOrder = Order.getInstance();

        if (!currentOrder.getPizzas().isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.confirm_order))
                    .setMessage(getString(R.string.confirm_order_message))
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        storeOrders.addOrder(currentOrder);
                        Order.resetInstance();
                        Toast.makeText(this,getString(R.string.order_placed_successfully), Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton(getString(R.string.no), (dialog, which) -> {

                        dialog.dismiss();
                    })
                    .show();
        } else {
            Toast.makeText(this, getString(R.string.no_pizzas_in_order), Toast.LENGTH_SHORT).show();
        }
    }





    class CurrentOrderAdapter extends ArrayAdapter<String> {
        private int selectedIndex = -1;
        private Context context;

        public CurrentOrderAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
            this.context = context;
        }

        public void setSelectedIndex(int index) {
            selectedIndex = index;
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            if (position == selectedIndex) {
                view.setBackgroundColor(Color.LTGRAY);
            } else {
                view.setBackgroundColor(Color.TRANSPARENT);
            }
            return view;
        }
    }

}







