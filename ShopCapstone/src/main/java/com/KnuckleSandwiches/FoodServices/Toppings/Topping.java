package com.KnuckleSandwiches.FoodServices.Toppings;

public abstract class Topping {
    private final String name;
    private final String category;
    private boolean isExtra;

    public Topping(String name, String category) {
        this.name = name;
        this.category = category;
        this.isExtra = false;
    }


    public String getName() { return name; }
    public String getCategory() { return category; }
    public boolean isExtra() { return isExtra; }

    public void setIsExtra(boolean isExtra) { this.isExtra = isExtra; }


    public abstract double calculateCost(String size);
}
