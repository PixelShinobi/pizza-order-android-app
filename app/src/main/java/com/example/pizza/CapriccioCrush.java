package com.example.pizza;

public class CapriccioCrush extends Pizza{

    public CapriccioCrush(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.toppings.add(Topping.CHERRY_TOMATOS);
        this.toppings.add(Topping.BASIL_LEAVES);
        this.toppings.add(Topping.BALSAMIC_GLAZE);
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[CapriccioCrush] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[CapriccioCrush] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[CapriccioCrush] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[CapriccioCrush] " + this.toppings.toString() + this.sauce.toString() + this.price();
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
                price = 22.99;
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




