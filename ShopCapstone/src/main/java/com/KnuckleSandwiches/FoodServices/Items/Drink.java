package com.KnuckleSandwiches.FoodServices.Items;

public class Drink extends MenuItem {

    private String size;
    private String flavor;

    public Drink(double price, String size, String flavor) {
        super(price);
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }
}
