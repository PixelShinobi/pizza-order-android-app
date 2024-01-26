package com.example.pizza;

public class BuffaloRanch extends Pizza{

    public BuffaloRanch(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.toppings.add(Topping.SPICY_BUFFALO_CHICKEN);
        this.toppings.add(Topping.RED_BELL_PEPPERS);
        this.toppings.add(Topping.RED_ONIONS);
        this.toppings.add(Topping.JALAPENOS);
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[BuffaloRanch]" + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[BuffaloRanch]" + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[BuffaloRanch]" + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[BuffaloRanch]" + this.toppings.toString() + this.sauce.toString() + this.price();
    }

    @Override
    public double price() {
        double price = 0;
        switch (this.size){
            case SMALL:
                price = 15.99;
                break;
            case MEDIUM:
                price = 17.99;
                break;
            case LARGE:
                price = 21.99;
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
