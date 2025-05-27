package com.KnuckleSandwiches.FoodServices.Toppings;


public class RegularTopping extends Topping {

    public RegularTopping(String name) {
        super(name);
    }


    @Override
    public double calculateCost(String size) {
        return 0.0;
    }
}
