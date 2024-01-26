package com.example.pizza;

public class Deluxe extends Pizza{
    public Deluxe(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.toppings.add(Topping.SAUSAGE);
        this.toppings.add(Topping.PEPPERONI);
        this.toppings.add(Topping.GREEN_PEPPER);
        this.toppings.add(Topping.ONION);
        this.toppings.add(Topping.MUSHROOM);
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[Deluxe] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + this.price();
        else if (extraSauce)
            return "[Deluxe] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce " + this.price();
        else if (extraCheese && extraSauce)
            return "[Deluxe] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce " + this.price();
        else
            return "[Deluxe] " + this.toppings.toString() + this.sauce.toString() + this.price();

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
                price = 18.99;
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
