package com.KnuckleSandwiches.FoodServices.Toppings;

import com.KnuckleSandwiches.FoodServices.Items.MenuItem;

public abstract class Topping extends MenuItem {
    private String name;
    private boolean isExtra;

    public Topping(double price, String name, boolean isExtra) {
        super(price);
        this.name = name;
        this.isExtra = isExtra;
    }

    public Topping() {
        super();
    }

    public String getName() { return name; }
    public boolean isExtra() { return isExtra; }

    public void setName(String name) { this.name = name; }
    public void setExtra(boolean isExtra) { this.isExtra = isExtra; }

    public abstract double calculateCost(String size);

}
