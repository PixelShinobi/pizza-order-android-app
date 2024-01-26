package com.example.pizza;

public class BBQFusion extends Pizza{

    public BBQFusion(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.toppings.add(Topping.GRILLED_CHICKEN_CHUNKS);
        this.toppings.add(Topping.CARAMELIZED_ONIONS);
        this.toppings.add(Topping.PINEAPPLE_CHUNKS);
        this.toppings.add(Topping.SPINACH);
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[BBQFusion] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[BBQFusion] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[BBQFusion] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[BBQFusion] " + this.toppings.toString() + this.sauce.toString() + this.price();
    }

    @Override
    public double price() {
        double price = 0;
        switch (this.size){
            case SMALL:
                price = 14.99;
                break;
            case MEDIUM:
                price = 16.99;
                break;
            case LARGE:
                price = 20.99;
                break;
        }
        if(this.extraCheese){
            price += 1;
        }
        if(this.extraSauce){
            price += 1;
        }
        return price;
    }



}
