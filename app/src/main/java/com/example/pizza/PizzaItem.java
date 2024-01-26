package com.example.pizza;




public class PizzaItem {

    private String imageUrl;
    private String name;
    private String toppings;

    private String sauce;
    private double basePrice;

    private boolean extraCheese;
    private boolean extraSauce;


    public PizzaItem(String imageUrl, String name, String toppings, String sauce, double basePrice){
        this.imageUrl = imageUrl;
        this.name = name;
        this.toppings = toppings;
        this.sauce = sauce;
        this.basePrice = basePrice;
        this.extraCheese = false;
        this.extraSauce = false;
    }


    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public double getFinalPrice(String size) {
        double finalPrice = basePrice;
        if ("Medium".equals(size)) {
            finalPrice += 2;
        } else if ("Large".equals(size)) {
            finalPrice += 4;
        }

        if (extraCheese) {
            finalPrice += 1;
        }

        if (extraSauce) {
            finalPrice += 1;
        }

        return finalPrice;
    }


    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getToppings() {
        return toppings;
    }

    public double getPrice() {
        return basePrice;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public void setPrice(double price) {
        this.basePrice = price;
    }

    public String getSauce() {
        return sauce;
    }


    public boolean isExtraCheese() {
        return extraCheese;
    }

    public boolean isExtraSauce() {
        return extraSauce;
    }





}
