package com.example.pizza;

public class MediVeggieDelight extends Pizza{
    public MediVeggieDelight(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.toppings.add(Topping.SUN_DRIED_TOMATOS);
        this.toppings.add(Topping.ARTICHOKE_HEARTS);
        this.toppings.add(Topping.KALAMATA_OLIVES);
        this.toppings.add(Topping.SPINACH);
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[MediVeggieDelight] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[MediVeggieDelight] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[MediVeggieDelight] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[MediVeggieDelight] " + this.toppings.toString() + this.sauce.toString() + this.price();

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
