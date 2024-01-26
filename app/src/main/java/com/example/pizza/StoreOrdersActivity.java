package com.example.pizza;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner spinnerOrderNumber;
    private TextView tvOrderTotal;
    private ListView lvOrderList;
    private Button btnCancelOrder, btnGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        // Corrected variable names
        spinnerOrderNumber = findViewById(R.id.spinnerOrderNumber);
        tvOrderTotal = findViewById(R.id.tvOrderTotal);
        lvOrderList = findViewById(R.id.lvOrderList);
        btnCancelOrder = findViewById(R.id.btnCancelOrder);
        btnGoBack = findViewById(R.id.btnGoBack);

        refresh();

        spinnerOrderNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSelectedOrderDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnCancelOrder.setOnClickListener(v -> handleCancelOrder());
        btnGoBack.setOnClickListener(v -> finish()); // Go back to the previous activity
    }

    public void refresh() {
        updateOrderList();
        updateOrderNumbers();
    }

    private void updateOrderList() {
        StoreOrders storeOrders = StoreOrders.getInstance();
        List<String> orderDescriptions = new ArrayList<>();
        for (Order order : storeOrders.getOrders()) {
            orderDescriptions.add("Order ID: " + order.getOrderId());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderDescriptions);
        lvOrderList.setAdapter(adapter);
    }

    private void updateOrderNumbers() {
        StoreOrders storeOrders = StoreOrders.getInstance();
        List<String> orderNumbers = new ArrayList<>();
        for (Order order : storeOrders.getOrders()) {
            orderNumbers.add(String.valueOf(order.getOrderId()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
        spinnerOrderNumber.setAdapter(adapter);
    }

    private void updateSelectedOrderDisplay() {
        String selectedOrderId = (String) spinnerOrderNumber.getSelectedItem();
        if (selectedOrderId != null) {
            int orderId = Integer.parseInt(selectedOrderId);
            StoreOrders storeOrders = StoreOrders.getInstance();
            Order selectedOrder = storeOrders.getOrderById(orderId);
            if (selectedOrder != null) {
                updateOrderDetails(selectedOrder);
            }
        }
    }

    private void updateOrderDetails(Order order) {
        List<String> pizzaDescriptions = new ArrayList<>();
        for (Pizza pizza : order.getPizzas()) {
            pizzaDescriptions.add(pizza.getDescription());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaDescriptions);
        lvOrderList.setAdapter(adapter);
        tvOrderTotal.setText(String.format("$%.2f", order.getTotalPrice()));
    }

    private void handleCancelOrder() {
        String selectedOrderId = (String) spinnerOrderNumber.getSelectedItem();
        if (selectedOrderId != null) {
            int orderId = Integer.parseInt(selectedOrderId);
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.cancel_order_confirmation_title))
                    .setMessage(getString(R.string.cancel_order_confirmation_message))
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        StoreOrders storeOrders = StoreOrders.getInstance();
                        storeOrders.removeOrderById(orderId);
                        refresh();
                        Toast.makeText(this, getString(R.string.order_canceled), Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.dismiss())
                    .show();
        } else {
            Toast.makeText(this, getString(R.string.select_order_to_cancel), Toast.LENGTH_SHORT).show();
        }
    }



}


