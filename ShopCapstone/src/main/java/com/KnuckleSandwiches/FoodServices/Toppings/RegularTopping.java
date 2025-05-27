package com.KnuckleSandwiches.FoodServices.Toppings;

import java.util.Map;

public class RegularTopping extends Topping {

    public RegularTopping(double price, String name, boolean isExtra) {
        super(price, name, isExtra);
    }


    @Override
    public double calculatePrice() {
        return getPrice();
    }

    @Override
    public double calculateCost(String size) {
        return calculatePrice();
    }
}
