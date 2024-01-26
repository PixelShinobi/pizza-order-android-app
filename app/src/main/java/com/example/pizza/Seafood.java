package com.example.pizza;

public class Seafood extends Pizza{
    public Seafood(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;

        this.toppings.add(Topping.SHRIMP);
        this.toppings.add(Topping.SQUID);
        this.toppings.add(Topping.CRAB_MEATS);
        this.sauce = Sauce.ALFREDO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }



    @Override
    public String getDescription() {
        if (extraCheese)
            return "[Seafood] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[Seafood] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[Seafood] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[Seafood] " + this.toppings.toString() + this.sauce.toString() + this.price();

    }

    @Override
    public double price() {
        double price = 0;
        switch (this.size){
            case SMALL:
                price = 17.99;
                break;
            case MEDIUM:
                price = 19.99;
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
