package com.example.pizza;

public class BuildYourOwn extends Pizza{

    public BuildYourOwn(Size size, Topping[] toppings, Sauce sauce, boolean extraSauce, boolean extraCheese){
        super();
        this.size = size;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
        for(Topping topping : toppings){
            this.toppings.add(topping);
        }

    }



    @Override
    public String getDescription() {
        if (extraCheese)
            return "[Build Your Own] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese "+ this.price();
        else if (extraSauce)
            return "[Build Your Own] " + this.toppings.toString() + this.sauce.toString() + " Extra Sauce "+ this.price();
        else if (extraCheese && extraSauce)
            return "[Build Your Own] " + this.toppings.toString() + this.sauce.toString() + " Extra Cheese " + " Extra Sauce "+ this.price();
        else
            return "[Build Your Own] " + this.toppings.toString() + this.sauce.toString() + this.price();

    }



    @Override
    public double price() {
        double price = 0;
        switch (this.size){
            case SMALL:
                price = 8.99;
                break;
            case MEDIUM:
                price = 10.99;
                break;
            case LARGE:
                price = 12.99;
                break;

        }
        if(this.extraCheese){
            price += 1;
        }
        if(this.extraSauce){
            price += 1;
        }


        if(this.toppings.size() > 3){
            price += (this.toppings.size() - 3) * 1.49;
        }

        return price;

    }
}
