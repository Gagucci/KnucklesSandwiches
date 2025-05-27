package com.KnuckleSandwiches.FoodServices.Toppings;


import java.util.ArrayList;
import java.util.List;

public class PremiumTopping extends Topping {
    private double basePrice;
    private double extraPrice;


    public PremiumTopping(double price, String name, boolean isExtra, double basePrice, double extraPrice) {
        super(price, name, isExtra);
        this.basePrice = basePrice;
        this.extraPrice = extraPrice;
    }

    public PremiumTopping() {
        super();
    }

    public double getBasePrice() { return basePrice; }
    public double getExtraPrice() { return extraPrice; }

    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public void setExtraPrice(double extraPrice) { this.extraPrice = extraPrice; }

    @Override
    public double calculatePrice() {
        return getPrice() + (isExtra() ? extraPrice : basePrice);
    }

    @Override
    public double calculateCost(String size) {
        return calculatePrice();
    }

}

