package com.example.pizza;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import android.util.Log;




public class BuildYourOwnActivity extends AppCompatActivity {

    private RadioButton tomatoSauceButton, alfredoSauceButton;
    private CheckBox extraSauceBox, extraCheeseBox;
    private Spinner pizzaSizeSpinner;
    private ListView additionalToppingsList, selectedToppingsList;
    private Button addToppingButton, removeToppingButton, addToOrderButton, goBackButton;
    private EditText priceOutput;

    private CustomArrayAdapter additionalToppingsAdapter;
    private CustomArrayAdapter selectedToppingsAdapter;


    private final List<String> toppingsOptions = Arrays.asList(
            "Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom",
            "Shrimp", "Squid", "Crab Meats", "Beef", "Ham",
            "Black Olive", "Chicken", "Bacon"
    );

    private String selectedAdditionalTopping = null;
    private String selectedSelectedTopping = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_your_own);

        initializeViews();
        setupSpinners();
        setupListView();
        setupListeners();
        updatePriceDisplay();
    }

    private void initializeViews() {
        tomatoSauceButton = findViewById(R.id.tomato_sauce_button);
        alfredoSauceButton = findViewById(R.id.alfredo_sauce_button);
        extraSauceBox = findViewById(R.id.extra_sauce_box);
        extraCheeseBox = findViewById(R.id.extra_cheese_box);
        pizzaSizeSpinner = findViewById(R.id.pizza_size);
        additionalToppingsList = findViewById(R.id.additional_toppings_list);
        selectedToppingsList = findViewById(R.id.selected_toppings_list);
        addToppingButton = findViewById(R.id.add_topping_button);
        removeToppingButton = findViewById(R.id.remove_topping_button);
        addToOrderButton = findViewById(R.id.add_to_order_button);
        goBackButton = findViewById(R.id.go_back_button);
        priceOutput = findViewById(R.id.price_output);
    }

    private void setupSpinners() {
        ArrayAdapter<String> pizzaSizeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                Arrays.asList("Small", "Medium", "Large"));
        pizzaSizeSpinner.setAdapter(pizzaSizeAdapter);
        pizzaSizeSpinner.setSelection(0); // Default to "Small"
    }

    private void setupListView() {

        additionalToppingsAdapter = new CustomArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                toppingsOptions);
        additionalToppingsList.setAdapter(additionalToppingsAdapter);
        additionalToppingsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        selectedToppingsAdapter = new CustomArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>());
        selectedToppingsList.setAdapter(selectedToppingsAdapter);
        selectedToppingsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        additionalToppingsList.setOnItemClickListener((parent, view, position, id) -> {
            selectedAdditionalTopping = (String) parent.getItemAtPosition(position);
            additionalToppingsAdapter.setSelectedIndex(position);

        });


        selectedToppingsList.setOnItemClickListener((parent, view, position, id) -> {
            selectedSelectedTopping = (String) parent.getItemAtPosition(position);
            selectedToppingsAdapter.setSelectedIndex(position);

        });
    }


    private void setupListeners() {
        pizzaSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePriceDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        extraSauceBox.setOnClickListener(v -> updatePriceDisplay());
        extraCheeseBox.setOnClickListener(v -> updatePriceDisplay());

        addToppingButton.setOnClickListener(v -> handleAddButtonAction());
        removeToppingButton.setOnClickListener(v -> handleRemoveButtonAction());
        addToOrderButton.setOnClickListener(v -> addToOrder());
        goBackButton.setOnClickListener(v -> finish());

    }

    private double calculatePrice() {
        double basePrice = 8.99;
        double toppingPrice = 1.49;

        String size = pizzaSizeSpinner.getSelectedItem().toString();
        if ("Medium".equals(size)) {
            basePrice += 2;
        } else if ("Large".equals(size)) {
            basePrice += 4;
        }

        int additionalToppingsCount = selectedToppingsAdapter.getCount() - 3;
        if (additionalToppingsCount > 0) {
            basePrice += additionalToppingsCount * toppingPrice;
        }

        if (extraSauceBox.isChecked()) {
            basePrice += 1;
        }
        if (extraCheeseBox.isChecked()) {
            basePrice += 1;
        }

        return basePrice;
    }

    private void updatePriceDisplay() {
        double price = calculatePrice();
        priceOutput.setText(String.format("$%.2f", price));
    }

    private void handleAddButtonAction() {


        if (selectedAdditionalTopping == null) {
            Toast.makeText(this, getString(R.string.select_topping_to_add), Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedToppingsAdapter.getCount() >= 7) {
            Toast.makeText(this, getString(R.string.max_toppings_reached), Toast.LENGTH_SHORT).show();
            return;
        }

        selectedToppingsAdapter.add(selectedAdditionalTopping);
        additionalToppingsAdapter.remove(selectedAdditionalTopping);

        selectedAdditionalTopping = null;



        refreshSelectedToppingsListView();
        refreshAdditionalToppingsListView();


        updatePriceDisplay();


    }



    private void handleRemoveButtonAction() {

        if (selectedSelectedTopping == null) {
            Toast.makeText(this, getString(R.string.select_topping_to_remove), Toast.LENGTH_SHORT).show();
            return;
        }

        additionalToppingsAdapter.add(selectedSelectedTopping);
        selectedToppingsAdapter.remove(selectedSelectedTopping);
        selectedSelectedTopping = null;

        refreshSelectedToppingsListView();
        refreshAdditionalToppingsListView();

        updatePriceDisplay();
    }


    private void refreshSelectedToppingsListView() {
        List<String> currentSelectedItems = new ArrayList<>(selectedToppingsAdapter.getItems());
        selectedToppingsAdapter = new CustomArrayAdapter(this, android.R.layout.simple_list_item_1, currentSelectedItems);
        selectedToppingsList.setAdapter(selectedToppingsAdapter);
    }


    private void refreshAdditionalToppingsListView() {
        List<String> currentAdditionalItems = new ArrayList<>(additionalToppingsAdapter.getItems());
        additionalToppingsAdapter = new CustomArrayAdapter(this, android.R.layout.simple_list_item_1, currentAdditionalItems);
        additionalToppingsList.setAdapter(additionalToppingsAdapter);
    }



    private void addToOrder() {
        String selectedPizzaType = "BuildYourOwn";

        // Assuming pizzaSizeSpinner is a Spinner
        String selectedSizeValue = pizzaSizeSpinner.getSelectedItem().toString();
        Size selectedSize = Size.SMALL; // Adjust Size enum as per your implementation
        if ("Medium".equals(selectedSizeValue)) {
            selectedSize = Size.MEDIUM;
        } else if ("Large".equals(selectedSizeValue)) {
            selectedSize = Size.LARGE;
        }

        // Assuming selectedToppingsAdapter is an ArrayAdapter<String>
        List<String> selectedToppings = new ArrayList<>();
        for (int i = 0; i < selectedToppingsAdapter.getCount(); i++) {
            selectedToppings.add(selectedToppingsAdapter.getItem(i));
        }
        Topping[] toppings = new Topping[selectedToppings.size()];
        for (int i = 0; i < selectedToppings.size(); i++) {
            String topping = selectedToppings.get(i);
            toppings[i] = Topping.valueOf(topping.toUpperCase().replace(" ", "_"));
        }

        // Adjust Sauce enum as per your implementation
        Sauce sauce = Sauce.TOMATO;
        if (alfredoSauceButton.isChecked()) { // Assuming alfredoSauceButton is a RadioButton or CheckBox
            sauce = Sauce.ALFREDO;
        }

        boolean extraSauce = extraSauceBox.isChecked(); // Assuming extraSauceBox is a CheckBox
        boolean extraCheese = extraCheeseBox.isChecked(); // Assuming extraCheeseBox is a CheckBox

        Pizza pizza = PizzaMaker.createPizza(selectedPizzaType, selectedSize, toppings, sauce, extraSauce, extraCheese);
        Order order = Order.getInstance();
        order.addPizza(pizza);


        Toast.makeText(this, getString(R.string.pizza_added_to_order), Toast.LENGTH_LONG).show();
    }








}






 class CustomArrayAdapter extends ArrayAdapter<String> {
    private int selectedIndex = -1;
    private Context context;
     private List<String> items;

     public CustomArrayAdapter(Context context, int textViewResourceId, List<String> items) {
         super(context, textViewResourceId, items);
         this.context = context;
         this.items = new ArrayList<>(items);  // Create a new ArrayList from the items
     }

    public void setSelectedIndex(int index) {
        selectedIndex = index;
        notifyDataSetChanged();
    }

     public List<String> getItems() {
         return new ArrayList<>(items);
     }

     @Override
     public void clear() {
         items.clear();
         notifyDataSetChanged();
     }





     @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        if (position == selectedIndex) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_item)); // Color for selected item
        } else {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.default_item)); // Color for default item
        }
        return view;
    }


     @Override
     public void add(String item) {
         items.add(item);
         notifyDataSetChanged();
     }

     @Override
     public void remove(String item) {
         items.remove(item);
         notifyDataSetChanged();
     }



 }
















