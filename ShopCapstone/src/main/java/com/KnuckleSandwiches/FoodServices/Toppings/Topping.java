package com.KnuckleSandwiches.FoodServices.Toppings;

public abstract class Topping {
    private String name;
    private boolean isExtra;

    public Topping(String name) {
        this.name = name;
        this.isExtra = false;
    }


    public String getName() { return name; }
    public boolean isExtra() { return isExtra; }

    public void setExtra(boolean isExtra) { this.isExtra = isExtra; }


    public abstract double calculateCost(String size);
}
