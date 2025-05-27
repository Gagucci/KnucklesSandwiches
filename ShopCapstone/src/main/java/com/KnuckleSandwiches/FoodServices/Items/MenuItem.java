package com.KnuckleSandwiches.FoodServices.Items;

import com.KnuckleSandwiches.Interfaces.Priceable;

public abstract class MenuItem implements Priceable {
    private double price;

    public MenuItem(double price) {
        this.price = price;
    }

    public MenuItem() {
    }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public abstract double calculatePrice();
}
