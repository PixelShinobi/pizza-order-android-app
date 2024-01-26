package com.example.pizza;
public class Meatzza extends Pizza{

    public Meatzza(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;

        this.toppings.add(Topping.SAUSAGE);
        this.toppings.add(Topping.PEPPERONI);
        this.toppings.add(Topping.BEEF);
        this.toppings.add(Topping.HAM);
        this.sauce = Sauce.TOMATO;
        this.extraSauce =  extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[Meatzza] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + this.price();
        else if (extraSauce)
            return "[Meatzza] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce " + this.price();
        else if (extraCheese && extraSauce)
            return "[Meatzza] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce " + this.price();
        else
            return "[Meatzza] " + this.toppings.toString() + this.sauce.toString();

    }


    @Override
    public double price() {
        double price = 0;
        switch (this.size){
            case SMALL:
                price = 16.99;
                break;
            case MEDIUM:
                price = 18.99;
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
