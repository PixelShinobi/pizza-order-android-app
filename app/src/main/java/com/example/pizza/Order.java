package com.example.pizza;

import java.util.ArrayList;


public class Order {
    private static int nextOrderId = 1;
    private int orderId;
    private ArrayList<Pizza> pizzas;

    private static Order instance;

    public Order() {
        this.orderId = nextOrderId++;
        this.pizzas = new ArrayList<>();
    }

    public static void resetInstance() {
        instance = new Order();
    }

    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }


    public ArrayList<Pizza> getPizzas() {
        return this.pizzas;
    }

    public void addPizza(Pizza pizza) {
        this.pizzas.add(pizza);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Pizza pizza : this.pizzas) {
            totalPrice += pizza.price();
        }
        return totalPrice;
    }

    public int getOrderId() {
        return this.orderId;
    }


}



