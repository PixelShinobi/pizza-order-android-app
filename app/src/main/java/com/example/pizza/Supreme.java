package com.example.pizza;

public class Supreme extends Pizza{

    public Supreme(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.toppings.add(Topping.SAUSAGE);
        this.toppings.add(Topping.PEPPERONI);
        this.toppings.add(Topping.HAM);
        this.toppings.add(Topping.GREEN_PEPPER);
        this.toppings.add(Topping.ONION);
        this.toppings.add(Topping.BLACK_OLIVE);
        this.toppings.add(Topping.MUSHROOM);
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }


    @Override
    public String getDescription() {
        if (extraCheese)
            return "[Supreme] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[Supreme] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[Supreme] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[Supreme] " + this.toppings.toString() + this.sauce.toString() + this.price();

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
                price = 19.99;
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
