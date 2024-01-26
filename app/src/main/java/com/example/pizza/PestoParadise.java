package com.example.pizza;

public class PestoParadise extends Pizza{

    public PestoParadise(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.toppings.add(Topping.GRILLED_PRAWNS);
        this.toppings.add(Topping.CHERRY_TOMATOS);
        this.toppings.add(Topping.RED_ONIONS);
        this.toppings.add(Topping.PINE_NUTS);
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[PestoParadise] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[PestoParadise] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[PestoParadise] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[PestoParadise] " + this.toppings.toString() + this.sauce.toString() + this.price();
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
