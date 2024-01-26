package com.example.pizza;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.Toast;


public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private List<PizzaItem> pizzaItems;

    private Context context;
    public PizzaAdapter(Context context, List<PizzaItem> pizzaItems) {
        this.context = context;
        this.pizzaItems = pizzaItems;
    }




    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new PizzaViewHolder(view);
    }

    public void onBindViewHolder(PizzaViewHolder holder, int position) {
        PizzaItem item = pizzaItems.get(position);


        String pizzaInfo = item.getName() + "\nToppings: " + item.getToppings() + "\nSauce: " + item.getSauce();
        holder.pizzasInfoTextView.setText(pizzaInfo);


        int imageResId = holder.itemView.getContext().getResources().getIdentifier(
                item.getImageUrl(), "drawable", holder.itemView.getContext().getPackageName());
        holder.pizzaImageView.setImageResource(imageResId);


        updatePrice(holder, item, "Small");


        holder.radioGroupSize.setOnCheckedChangeListener((group, checkedId) -> {
            String size = getSizeFromRadioButton(checkedId);
            updatePrice(holder, item, size);
        });

        holder.extraCheeseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setExtraCheese(isChecked);
            String size = getSizeFromRadioButton(holder.radioGroupSize.getCheckedRadioButtonId());
            updatePrice(holder, item, size);
        });

        holder.extraSauceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setExtraSauce(isChecked);
            String size = getSizeFromRadioButton(holder.radioGroupSize.getCheckedRadioButtonId());
            updatePrice(holder, item, size);
        });


        holder.addToOrderButton.setOnClickListener(v -> addToOrder(item, holder ));
    }



    private void addToOrder(PizzaItem item, PizzaViewHolder holder) {
        String selectedPizzaType = item.getName();
        Size selectedSize = Size.SMALL;


        int checkedId = holder.radioGroupSize.getCheckedRadioButtonId();
        if (checkedId == R.id.medium_button) {
            selectedSize = Size.MEDIUM;
        } else if (checkedId == R.id.large_button) {
            selectedSize = Size.LARGE;
        }


        String[] toppingStrings = item.getToppings().split(", ");
        Topping[] toppings = new Topping[toppingStrings.length];
        for (int i = 0; i < toppingStrings.length; i++) {
            toppings[i] = Topping.valueOf(toppingStrings[i].toUpperCase().replace(" ", "_"));
        }


        Sauce sauce = Sauce.valueOf(item.getSauce().toUpperCase().replace(" ", "_"));

        boolean extraSauce = holder.extraSauceCheckBox.isChecked();
        boolean extraCheese = holder.extraCheeseCheckBox.isChecked();


        Pizza pizza = PizzaMaker.createPizza(selectedPizzaType, selectedSize, toppings, sauce, extraSauce, extraCheese);
        Order order = Order.getInstance();
        order.addPizza(pizza);


        Toast.makeText(context, context.getString(R.string.pizza_added_to_order), Toast.LENGTH_LONG).show();

    }



    private Size convertStringToSize(String sizeStr) {
        switch (sizeStr) {
            case "Medium":
                return Size.MEDIUM;
            case "Large":
                return Size.LARGE;
            default:
                return Size.SMALL;
        }
    }

    private void setupPriceLogic(PizzaViewHolder holder, PizzaItem item) {
        updatePrice(holder, item, "Small");

        holder.radioGroupSize.setOnCheckedChangeListener((group, checkedId) -> {
            String size = getSizeFromRadioButton(checkedId);
            updatePrice(holder, item, size);
        });

        holder.extraCheeseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setExtraCheese(isChecked);

            String size = getSizeFromRadioButton(holder.radioGroupSize.getCheckedRadioButtonId());
            updatePrice(holder, item, size);
        });

        holder.extraSauceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setExtraSauce(isChecked);

            String size = getSizeFromRadioButton(holder.radioGroupSize.getCheckedRadioButtonId());
            updatePrice(holder, item, size);
        });
    }




    private String getSizeFromRadioButton(int checkedId) {
        if (checkedId == R.id.medium_button) {
            return "Medium";
        } else if (checkedId == R.id.large_button) {
            return "Large";
        }
        return "Small";
    }

    private void updatePrice(PizzaViewHolder holder, PizzaItem item, String size) {
        double price = item.getFinalPrice(size);
        holder.priceTextView.setText(String.format("$%.2f", price));
    }

    @Override
    public int getItemCount() {
        return pizzaItems.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        ImageView pizzaImageView;
        TextView pizzasInfoTextView;
        TextView priceTextView;
        RadioGroup radioGroupSize;
        CheckBox extraCheeseCheckBox, extraSauceCheckBox;
        Button addToOrderButton;

        public PizzaViewHolder(View itemView) {
            super(itemView);
            pizzaImageView = itemView.findViewById(R.id.pizza_image);
            pizzasInfoTextView = itemView.findViewById(R.id.pizzas_info);
            priceTextView = itemView.findViewById(R.id.price_text);
            radioGroupSize = itemView.findViewById(R.id.radio_group_size);
            extraCheeseCheckBox = itemView.findViewById(R.id.extra_cheese);
            extraSauceCheckBox = itemView.findViewById(R.id.extra_sauce);
            addToOrderButton = itemView.findViewById(R.id.add_to_order);
        }
    }

}