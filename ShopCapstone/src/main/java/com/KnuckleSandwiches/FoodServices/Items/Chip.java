package com.KnuckleSandwiches.FoodServices.Items;

public class Chip extends MenuItem {

    private String type;

    public Chip(double price, String type) {
        super(price);
        this.type = type;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public double calculatePrice() {
        return getPrice();
    }
}
