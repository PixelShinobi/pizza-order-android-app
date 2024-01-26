package com.example.pizza;

public class Pepperoni extends Pizza{

    public Pepperoni(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.toppings.add(Topping.PEPPERONI);
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[Pepperoni] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[Pepperoni] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[Pepperoni] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[Pepperoni] " + this.toppings.toString() + this.sauce.toString() + this.price();

    }

    @Override
    public double price() {
        double price = 0;
        switch (this.size){
            case SMALL:
                price = 10.99;
                break;
            case MEDIUM:
                price = 12.99;
                break;
            case LARGE:
                price = 14.99;
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
